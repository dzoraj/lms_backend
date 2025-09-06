package lmsprojekat.service.teachingservice;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.EvaluationAttempt;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class EvaluationAttemptService extends AbstractCrudService<EvaluationAttemptDTO, EvaluationAttempt, Long> {

    private final EvaluationAttemptRepository evaluationAttemptRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
    private final StudentInYearRepository studentInYearRepository;

    public EvaluationAttemptService(
        EvaluationAttemptRepository evaluationAttemptRepository,
        KnowledgeEvaluationRepository knowledgeEvaluationRepository,
        StudentInYearRepository studentInYearRepository
    ) {
        this.evaluationAttemptRepository = evaluationAttemptRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
        this.studentInYearRepository = studentInYearRepository;
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
}
