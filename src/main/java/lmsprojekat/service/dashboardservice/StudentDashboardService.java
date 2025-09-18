package lmsprojekat.service.dashboardservice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentProfileDTO;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.subjectrepo.CourseAttendanceRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.userrepo.StudentRepository;

@Service
public class StudentDashboardService {

    private final StudentRepository studentRepo;
    private final StudentInYearRepository siyRepo;
    private final CourseAttendanceRepository caRepo;
    private final EvaluationAttemptRepository evalAttemptRepo;

    public StudentDashboardService(StudentRepository studentRepo,
                                   StudentInYearRepository siyRepo,
                                   CourseAttendanceRepository caRepo,
                                   EvaluationAttemptRepository evalAttemptRepo) {
        this.studentRepo = studentRepo;
        this.siyRepo = siyRepo;
        this.caRepo = caRepo;
        this.evalAttemptRepo = evalAttemptRepo;
    }

    public StudentProfileDTO getDashboard(Long studentId) {
        return buildProfile(studentId);
    }

    private StudentProfileDTO buildProfile(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StudentProfileDTO dto = new StudentProfileDTO();
        dto.setId(studentId);
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAverageGrade(studentRepo.avgGrade(studentId));
        dto.setEspb(studentRepo.espbForPassed(studentId));

        dto.setEnrollments(siyRepo.findAllByStudentId(studentId).stream()
                .map(siy -> {
                    var e = new StudentProfileDTO.EnrollmentDTO();
                    e.id = siy.getId();
                    e.indexNumber = siy.getIndexNumber();
                    e.enrollmentDate = siy.getEnrollmentDate() != null ? siy.getEnrollmentDate().toString() : null;
                    e.studyYearId = siy.getStudyYear() != null ? siy.getStudyYear().getId() : null;
                    return e;
                }).toList());

        dto.setPassedExams(caRepo.findAllByStudentId(studentId).stream()
                .filter(ca -> ca.getKonacnaOcena() != null)
                .map(ca -> {
                    var p = new StudentProfileDTO.PassedExamDTO();
                    var subj = ca.getCourseRealization().getSubject();
                    p.subjectName = subj.getName();
                    p.espb = subj.getEspb();
                    p.grade = ca.getKonacnaOcena();
                    p.subjectId = subj.getId(); 
                    return p;
                }).toList());

        dto.setExamAttempts(evalAttemptRepo.findAllByStudentId(studentId).stream()
                .map(ea -> {
                    var a = new StudentProfileDTO.ExamAttemptDTO();
                    var ke = ea.getEvaluation();
                    var subj = ke.getCourseRealization().getSubject();
                    a.subjectName = subj.getName();
                    a.espb = subj.getEspb();
                    a.evaluationId = ke.getId();
                    a.points = ea.getPoints();
                    a.note = ea.getNote();
                    a.maxPoints = ke.getPoints();
                    a.testPassed = (a.maxPoints != null && a.points != null && a.points >= a.maxPoints / 2);
                    a.latest = resolveLatest(ea);
                    return a;
                }).toList());

        List<CourseAttendance> cas = caRepo.findAllByStudentIdWithRealizationAndSubject(studentId);
        dto.setAttendingSubjects(
            cas.stream()
               .map(CourseAttendance::getCourseRealization)
               .filter(Objects::nonNull)
               .map(cr -> cr.getSubject())
               .filter(Objects::nonNull)
               .collect(Collectors.toMap(
                   Subject::getId,
                   subj -> subj,
                   (a, b) -> a
               ))
               .values()
               .stream()
               .map(subj -> {
                   var s = new StudentProfileDTO.AttendingSubjectDTO();
                   s.subjectId = subj.getId();
                   s.name = subj.getName();
                   s.espb = subj.getEspb();
                   s.lectureCount = subj.getLectureCount();
                   s.labCount = subj.getLabCount();
                   s.mandatory = subj.getMandatory();

                   Integer earned = evalAttemptRepo.sumLatestPointsByStudentAndSubject(studentId, subj.getId());
                   Integer total = (subj.getGradingScheme() != null) ? subj.getGradingScheme().getTotalPoints() : null;

                   s.pointsEarned = earned != null ? earned : 0;
                   s.totalPoints = total;
                   s.progress = (total != null && total > 0)
                           ? (s.pointsEarned.doubleValue() / total.doubleValue())
                           : null;

                   return s;
               })
               .toList()
        );

        dto.setFailedExams(List.of());
        dto.setInfractions(List.of());
        dto.setRegisteredExams(List.of());
        dto.setThesis(null);

        return dto;
    }

    private Boolean resolveLatest(Object ea) {
        try {
            var m1 = ea.getClass().getMethod("getLatest");
            Object v1 = m1.invoke(ea);
            if (v1 instanceof Boolean) return (Boolean) v1;
            if (v1 instanceof Number) return ((Number) v1).intValue() == 1;
        } catch (Exception ignored) {}
        try {
            var m2 = ea.getClass().getMethod("isLatest");
            Object v2 = m2.invoke(ea);
            if (v2 instanceof Boolean) return (Boolean) v2;
            if (v2 instanceof Number) return ((Number) v2).intValue() == 1;
        } catch (Exception ignored) {}
        return null;
    }
}
