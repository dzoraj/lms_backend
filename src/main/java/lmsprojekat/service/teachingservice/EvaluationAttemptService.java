package lmsprojekat.service.teachingservice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.teaching.EvaluationAttempt;
import lmsprojekat.model.teaching.ExamApplication;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.subjectrepo.CourseAttendanceRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.teachingrepo.ExamApplicationRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class EvaluationAttemptService extends AbstractCrudService<EvaluationAttemptDTO, EvaluationAttempt, Long> {

    private final EvaluationAttemptRepository evaluationAttemptRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final StudentInYearRepository studentInYearRepository;
    private final ExamApplicationRepository examApplicationRepository;
    private final CourseAttendanceRepository courseAttendanceRepository;

    public EvaluationAttemptService(
            EvaluationAttemptRepository evaluationAttemptRepository,
            KnowledgeEvaluationRepository knowledgeEvaluationRepository,
            StudentInYearRepository studentInYearRepository,
            ExamApplicationRepository examApplicationRepository,
            CourseAttendanceRepository courseAttendanceRepository
    ) {
        this.evaluationAttemptRepository = evaluationAttemptRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
        this.studentInYearRepository = studentInYearRepository;
        this.examApplicationRepository = examApplicationRepository;
        this.courseAttendanceRepository = courseAttendanceRepository;
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
        entity.setEvaluation(evaluation);
        entity.setStudentInYear(studentInYear);

        return entity;
    }

    @Override
    protected void updateEntity(EvaluationAttempt entity, EvaluationAttemptDTO dto) {
        if (dto.getPoints() != null) {
            entity.setPoints(dto.getPoints());
        }
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
    }

    @Transactional
    public EvaluationAttemptDTO enterGrade(Long teacherId, Long examApplicationId, int points, String note) {
        ExamApplication application = examApplicationRepository.findAuthorizedApplication(examApplicationId, teacherId);
        if (application == null) {
            throw new SecurityException("Teacher is not authorized for this exam application.");
        }

        KnowledgeEvaluation exam = application.getKnowledgeEvaluation();
        if (exam == null) {
            throw new EntityNotFoundException("Exam not found for application.");
        }

        if (exam.getEndTime() == null || exam.getEndTime().plusDays(15).isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Grade entry period expired (15 days after exam).");
        }

        boolean exists = evaluationAttemptRepository.existsByEvaluation_IdAndStudentInYear_Id(
                exam.getId(),
                application.getStudentInYear().getId()
        );
        if (exists) {
            throw new IllegalStateException("Grade already entered for this student.");
        }

        EvaluationAttempt attempt = new EvaluationAttempt();
        attempt.setEvaluation(exam);
        attempt.setStudentInYear(application.getStudentInYear());
        attempt.setPoints(points);
        attempt.setNote(note);
        evaluationAttemptRepository.save(attempt);

        updateFinalSubjectGrade(application.getStudentInYear().getStudent().getId(),
                                exam.getCourseRealization().getSubject().getId());

        return toDTO(attempt);
    }

    private void updateFinalSubjectGrade(Long studentId, Long subjectId) {
        List<Integer> points = evaluationAttemptRepository.findPointsByStudentAndSubject(studentId, subjectId);

        if (points.isEmpty()) return;

        List<Integer> lastThree = points.stream().limit(3).toList();

        double avgPoints = lastThree.stream().mapToInt(Integer::intValue).average().orElse(0);

        int grade;
        if (avgPoints >= 90) grade = 10;
        else if (avgPoints >= 80) grade = 9;
        else if (avgPoints >= 70) grade = 8;
        else if (avgPoints >= 60) grade = 7;
        else if (avgPoints >= 51) grade = 6;
        else grade = 5;

        CourseAttendance ca = courseAttendanceRepository.findByStudentAndSubject(studentId, subjectId);
        if (ca != null) {
            ca.setKonacnaOcena(grade);
            courseAttendanceRepository.save(ca);
        }
    }

}
