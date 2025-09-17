package lmsprojekat.service.teachingservice;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.model.grading.GradeBoundary;
import lmsprojekat.model.grading.GradingScheme;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.teaching.EvaluationAttempt;
import lmsprojekat.model.teaching.ExamApplication;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.teachingrepo.ExamApplicationRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.subjectservice.CourseAttendanceService;
@Service
public class EvaluationAttemptService extends AbstractCrudService<EvaluationAttemptDTO, EvaluationAttempt, Long> {

    private final EvaluationAttemptRepository evaluationAttemptRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final StudentInYearRepository studentInYearRepository;
    private final ExamApplicationRepository examApplicationRepository;
    private final SubjectRepository subjectRepository;
    private final CourseAttendanceService courseAttendanceService;

    public EvaluationAttemptService(EvaluationAttemptRepository evaluationAttemptRepository,
                                    KnowledgeEvaluationRepository knowledgeEvaluationRepository,
                                    StudentInYearRepository studentInYearRepository,
                                    ExamApplicationRepository examApplicationRepository,
                                    SubjectRepository subjectRepository,
                                    CourseAttendanceService courseAttendanceService) {
        this.evaluationAttemptRepository = evaluationAttemptRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
        this.studentInYearRepository = studentInYearRepository;
        this.examApplicationRepository = examApplicationRepository;
        this.subjectRepository = subjectRepository;
        this.courseAttendanceService = courseAttendanceService;
    }

    @Override
    protected EvaluationAttemptRepository getRepository() {
        return evaluationAttemptRepository;
    }

    @Override
    public EvaluationAttemptDTO toDTO(EvaluationAttempt entity) {
        if (entity == null) return null;
        return new EvaluationAttemptDTO(
                entity.getId(),
                entity.getPoints(),
                entity.getNote(),
                entity.isLatest(),
                entity.getEvaluation() != null ? entity.getEvaluation().getId() : null,
                entity.getStudentInYear() != null ? entity.getStudentInYear().getId() : null
        );
    }

    @Override
    public EvaluationAttempt toEntity(EvaluationAttemptDTO dto) {
        if (dto == null) return null;

        KnowledgeEvaluation evaluation = knowledgeEvaluationRepository.findById(dto.getEvaluationId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + dto.getEvaluationId()));

        StudentInYear studentInYear = studentInYearRepository.findById(dto.getStudentInYearId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid StudentInYear ID: " + dto.getStudentInYearId()));

        EvaluationAttempt entity = new EvaluationAttempt();
        entity.setId(dto.getId());
        entity.setPoints(dto.getPoints());
        entity.setNote(dto.getNote());
        entity.setLatest(true);
        entity.setEvaluation(evaluation);
        entity.setStudentInYear(studentInYear);

        return entity;
    }

    @Override
    protected void updateEntity(EvaluationAttempt entity, EvaluationAttemptDTO dto) {
        if (dto.getPoints() != null) entity.setPoints(dto.getPoints());
        entity.setNote(dto.getNote());

        if (dto.getEvaluationId() != null) {
            KnowledgeEvaluation evaluation = knowledgeEvaluationRepository.findById(dto.getEvaluationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid KnowledgeEvaluation ID: " + dto.getEvaluationId()));
            entity.setEvaluation(evaluation);
        }

        if (dto.getStudentInYearId() != null) {
            StudentInYear studentInYear = studentInYearRepository.findById(dto.getStudentInYearId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid StudentInYear ID: " + dto.getStudentInYearId()));
            entity.setStudentInYear(studentInYear);
        }

        if (entity.getEvaluation() != null && entity.getStudentInYear() != null) {
            updateFinalSubjectGrade(
                    entity.getStudentInYear().getStudent().getId(),
                    entity.getEvaluation().getCourseRealization().getSubject().getId()
            );
        }
    }

    @Transactional
    public EvaluationAttemptDTO enterGrade(Long teacherId, Long examApplicationId, int points, String note) {
        ExamApplication application = examApplicationRepository.findAuthorizedApplication(examApplicationId, teacherId);
        if (application == null) throw new SecurityException("Teacher is not authorized for this exam application.");

        KnowledgeEvaluation exam = application.getKnowledgeEvaluation();
        if (exam == null) throw new EntityNotFoundException("Exam not found for application.");

        if (exam.getEndTime() == null || exam.getEndTime().plusDays(15).isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Grade entry period expired (15 days after exam).");
        }

        evaluationAttemptRepository.markOldAttemptsAsNotLatest(
                application.getStudentInYear().getId(),
                exam.getCourseRealization().getId(),
                exam.getEvaluationType().getId()
        );

        EvaluationAttempt attempt = new EvaluationAttempt();
        attempt.setEvaluation(exam);
        attempt.setStudentInYear(application.getStudentInYear());
        attempt.setPoints(points);
        attempt.setNote(note);
        attempt.setLatest(true);
        evaluationAttemptRepository.save(attempt);

        updateFinalSubjectGrade(
                application.getStudentInYear().getStudent().getId(),
                exam.getCourseRealization().getSubject().getId()
        );

        return toDTO(attempt);
    }

    public void updateFinalSubjectGrade(Long studentId, Long subjectId) {
        List<EvaluationAttempt> attempts = evaluationAttemptRepository.findLatestByStudentAndSubject(studentId, subjectId);
        if (attempts.isEmpty()) return;

        for (EvaluationAttempt attempt : attempts) {
            KnowledgeEvaluation ke = attempt.getEvaluation();
            if (ke != null && ke.getPoints() != null) {
                int max = ke.getPoints();
                int pts = attempt.getPoints() != null ? attempt.getPoints() : 0;
                if (pts < (max / 2)) {
                    courseAttendanceService.createOrUpdateFinalGrade(studentId, subjectId, 5);
                    return;
                }
            }
        }

        int totalPoints = attempts.stream()
                .mapToInt(a -> a.getPoints() != null ? a.getPoints() : 0)
                .sum();

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found id=" + subjectId));

        GradingScheme scheme = subject.getGradingScheme();
        if (scheme == null) throw new IllegalStateException("No grading scheme defined for subject " + subjectId);

        List<GradeBoundary> boundaries = scheme.getGradeBoundaries().stream()
                .sorted(Comparator.comparingInt(GradeBoundary::getMinPoints))
                .toList();

        int grade = 5;
        for (GradeBoundary boundary : boundaries) {
            if (totalPoints >= boundary.getMinPoints()) {
                grade = boundary.getGradeValue();
            }
        }

        courseAttendanceService.createOrUpdateFinalGrade(studentId, subjectId, grade);
    }
}
