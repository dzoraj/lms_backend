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
import lmsprojekat.repository.userrepo.StudentRepository;

@Service
public class StudentDirectoryService {

    private final StudentRepository studentRepo;
    private final StudentInYearRepository siyRepo;
    private final CourseAttendanceRepository caRepo;

    public StudentDirectoryService(StudentRepository studentRepo,
                                   StudentInYearRepository siyRepo,
                                   CourseAttendanceRepository caRepo) {
        this.studentRepo = studentRepo;
        this.siyRepo = siyRepo;
        this.caRepo = caRepo;
    }

    public List<StudentSearchDTO> teacherScopedSearch(Long teacherId, String name, String index, Integer enrollmentYear,
                                                      Double minAvg, Double maxAvg) {
        List<Student> base = studentRepo.teacherScopedSearch(
            teacherId,
            blankToNull(name),
            blankToNull(index),
            enrollmentYear
        );

        return base.stream().map(s -> {
            RegisteredUser ru = (RegisteredUser) s;
            Double avg = studentRepo.avgGrade(s.getId());
            Integer ects = studentRepo.ectsForPassed(s.getId());
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
                ects
            );
        }).filter(d -> d != null).collect(Collectors.toList());
    }

    public StudentProfileDTO profile(Long studentId) {
        Student s = studentRepo.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        RegisteredUser ru = (RegisteredUser) s;

        Double avg = studentRepo.avgGrade(studentId);
        Integer ects = studentRepo.ectsForPassed(studentId);

        var prof = new StudentProfileDTO();
        prof.setId(studentId);
        prof.setName(ru.getName());
        prof.setEmail(ru.getEmail());
        prof.setAverageGrade(avg);
        prof.setEcts(ects);

        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;

        List<StudentProfileDTO.EnrollmentDTO> enrollments = siyRepo.findById(studentId).stream().map(e -> {
            var dto = new StudentProfileDTO.EnrollmentDTO();
            dto.id = e.getId();
            dto.indexNumber = e.getIndexNumber();
            dto.enrollmentDate = e.getEnrollmentDate().format(fmt);
            dto.studyYearId = e.getStudyYear() != null ? e.getStudyYear().getId() : null;
            return dto;
        }).toList();
        prof.setEnrollments(enrollments);

        List<StudentProfileDTO.PassedExamDTO> passed = caRepo.findById(studentId).stream()
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

        prof.setFailedExams(List.of());       
        prof.setInfractions(List.of());      
        prof.setRegisteredExams(List.of());   
        prof.setThesis(null);                 // not modeled > null

        return prof;
    }

    private String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}
