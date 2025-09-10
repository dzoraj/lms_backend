package lmsprojekat.service.studentservice;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentProfileDTO;
import lmsprojekat.dto.studentdto.StudentSearchDTO;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.subjectrepo.CourseAttendanceRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.userrepo.StudentRepository;

@Service
public class StudentDirectoryService {

    private final StudentRepository studentRepo;
    private final StudentInYearRepository siyRepo;
    private final CourseAttendanceRepository caRepo;
    private final EvaluationAttemptRepository evaluationAttemptRepo;

    public StudentDirectoryService(StudentRepository studentRepo,
                                   StudentInYearRepository siyRepo,
                                   CourseAttendanceRepository caRepo,
                                   EvaluationAttemptRepository evaluationAttemptRepo) {
        this.studentRepo = studentRepo;
        this.siyRepo = siyRepo;
        this.caRepo = caRepo;
        this.evaluationAttemptRepo = evaluationAttemptRepo;
    }

    public List<StudentSearchDTO> teacherScopedSearch(Long teacherId, String name, String index, Integer enrollmentYear,
                                                      Double minAvg, Double maxAvg) {
        List<Student> base = studentRepo.teacherScopedSearch(
            teacherId,
            blankToNull(name),
            blankToNull(index),
            enrollmentYear
        );
        System.out.println("Teacher search - students found in DB: " + base.size());
        return mapToDTOs(base, minAvg, maxAvg);
    }

    public List<StudentSearchDTO> globalSearch(String name, String index, Integer enrollmentYear,
                                               Double minAvg, Double maxAvg) {
        List<Student> base = studentRepo.directorySearch(
            blankToNull(name),
            blankToNull(index),
            enrollmentYear
        );
        System.out.println("Global search - students found in DB: " + base.size());
        return mapToDTOs(base, minAvg, maxAvg);
    }

    private List<StudentSearchDTO> mapToDTOs(List<Student> base, Double minAvg, Double maxAvg) {
        return base.stream().map(s -> {
            RegisteredUser ru = (RegisteredUser) s;
            Double avg = studentRepo.avgGrade(s.getId());
            Integer espb = studentRepo.espbForPassed(s.getId());
            String idx = studentRepo.latestIndex(s.getId()).stream().findFirst().orElse(null);
            Integer enrYear = studentRepo.latestEnrollmentYear(s.getId()).stream().findFirst().orElse(null);

            if (minAvg != null && (avg == null || avg < minAvg)) return null;
            if (maxAvg != null && (avg != null && avg > maxAvg)) return null;

            return new StudentSearchDTO(
                s.getId(),
                ru.getName(),
                ru.getEmail(),
                idx,
                enrYear,
                avg,
                espb
            );
        }).filter(d -> d != null).collect(Collectors.toList());
    }

    public StudentProfileDTO profile(Long studentId) {
        Student s = studentRepo.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        RegisteredUser ru = (RegisteredUser) s;

        Double avg = studentRepo.avgGrade(studentId);
        Integer espb = studentRepo.espbForPassed(studentId);

        var prof = new StudentProfileDTO();
        prof.setId(studentId);
        prof.setName(ru.getName());
        prof.setEmail(ru.getEmail());
        prof.setAverageGrade(avg);
        prof.setEspb(espb);

        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

        var enrollments = siyRepo.findAllByStudentId(studentId).stream()
            .map(e -> {
                var dto = new StudentProfileDTO.EnrollmentDTO();
                dto.id = e.getId();
                dto.indexNumber = e.getIndexNumber();
                dto.enrollmentDate = e.getEnrollmentDate().format(fmt);
                dto.studyYearId = e.getStudyYear() != null ? e.getStudyYear().getId() : null;
                return dto;
            })
            .toList();
        prof.setEnrollments(enrollments);

        var passed = caRepo.findAllByStudentId(studentId).stream()
            .filter(ca -> ca.getKonacnaOcena() != null)
            .map(ca -> {
                CourseRealization cr = ca.getCourseRealization();
                Subject subj = cr != null ? cr.getSubject() : null;
                var dto = new StudentProfileDTO.PassedExamDTO();
                dto.subjectName = subj != null ? subj.getName() : null;
                dto.espb = subj != null ? subj.getEspb() : null;
                dto.grade = ca.getKonacnaOcena();
                dto.finalPoints = null;
                return dto;
            }).toList();
        prof.setPassedExams(passed);

        var attempts = evaluationAttemptRepo.findAllByStudentId(studentId).stream()
            .map(ea -> {
                var dto = new StudentProfileDTO.ExamAttemptDTO();
                var ke = ea.getEvaluation();
                var cr = ke != null ? ke.getCourseRealization() : null;
                var subj = cr != null ? cr.getSubject() : null;

                dto.subjectName = subj != null ? subj.getName() : null;
                dto.espb = subj != null ? subj.getEspb() : null;
                dto.evaluationId = ke != null ? ke.getId() : null;
                dto.points = ea.getPoints();
                dto.note = ea.getNote();
                return dto;
            }).toList();
        prof.setExamAttempts(attempts);

        prof.setFailedExams(List.of());
        prof.setInfractions(List.of());
        prof.setRegisteredExams(List.of());
        prof.setThesis(null);

        return prof;
    }

    private String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}
