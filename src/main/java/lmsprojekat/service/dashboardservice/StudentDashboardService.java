package lmsprojekat.service.dashboardservice;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentProfileDTO;
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
                    e.enrollmentDate = siy.getEnrollmentDate().toString();
                    e.studyYearId = siy.getStudyYear() != null ? siy.getStudyYear().getId() : null;
                    return e;
                }).toList());

        dto.setPassedExams(caRepo.findAllByStudentId(studentId).stream()
                .filter(ca -> ca.getKonacnaOcena() != null)
                .map(ca -> {
                    var p = new StudentProfileDTO.PassedExamDTO();
                    p.subjectName = ca.getCourseRealization().getSubject().getName();
                    p.espb = ca.getCourseRealization().getSubject().getEspb();
                    p.grade = ca.getKonacnaOcena();
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

        	        return a;
        	    }).toList());


        dto.setAttendingSubjects(
        	    evalAttemptRepo.findAllByStudentId(studentId).stream()
        	        .map(ea -> ea.getEvaluation()
        	                     .getCourseRealization()
        	                     .getSubject())
        	        .filter(Objects::nonNull)
        	        .distinct()
        	        .map(subj -> {
        	            var s = new StudentProfileDTO.AttendingSubjectDTO();
        	            s.subjectId = subj.getId();
        	            s.name = subj.getName();
        	            s.espb = subj.getEspb();
        	            s.lectureCount = subj.getLectureCount();
        	            s.labCount = subj.getLabCount();
        	            s.mandatory = subj.getMandatory();
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
}
