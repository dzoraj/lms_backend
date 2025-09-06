package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.teachingdto.EvaluationTypeDTO;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.model.teaching.EvaluationType;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.teachingrepo.EvaluationInstrumentRepository;
import lmsprojekat.repository.teachingrepo.EvaluationTypeRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class KnowledgeEvaluationService extends AbstractCrudService<KnowledgeEvaluationDTO, KnowledgeEvaluation, Long> {

    private KnowledgeEvaluationRepository knowledgeEvaluationRepository;


    private EvaluationInstrumentRepository evaluationInstrumentRepository;

    private EvaluationTypeRepository evaluationTypeRepository;

    private CourseRealizationRepository courseRealizationRepository;

    private LearningOutcomeRepository learningOutcomeRepository;

    @Override
    protected SoftDeleteRepository<KnowledgeEvaluation, Long> getRepository() {
        return knowledgeEvaluationRepository;
    }

    @Override
    public KnowledgeEvaluationDTO toDTO(KnowledgeEvaluation entity) {
        return new KnowledgeEvaluationDTO(
            entity.getId(),
            entity.getStartTime(),
            entity.getEndTime(),
            entity.getPoints(),
            new EvaluationInstrumentDTO(
                entity.getEvaluationInstrument().getId(),
                entity.getEvaluationInstrument().getName(),
                null,
                entity.getEvaluationInstrument().getFile()
            ),
            new EvaluationTypeDTO(
                entity.getEvaluationType().getId(),
                entity.getEvaluationType().getName(),
                null
            ),
            entity.getCourseRealization(),
            entity.getLearningOutcomes()
        );
    }

    @Override
	public KnowledgeEvaluation toEntity(KnowledgeEvaluationDTO dto) {
        EvaluationInstrument instrument = evaluationInstrumentRepository.findById(dto.getEvaluationInstrument().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationInstrument ID"));

        EvaluationType type = evaluationTypeRepository.findById(dto.getEvaluationType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationType ID"));

        CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealization().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid CourseRealization ID"));

        List<LearningOutcome> outcomes = dto.getLearningOutcomes().stream()
                .map(lo -> learningOutcomeRepository.findById(lo.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid LearningOutcome ID: " + lo.getId())))
                .collect(Collectors.toList());

        return new KnowledgeEvaluation(
                dto.getId(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getPoints(),
                instrument,
                type,
                realization,
                outcomes
        );
    }

    @Override
    protected void updateEntity(KnowledgeEvaluation entity, KnowledgeEvaluationDTO dto) {
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setPoints(dto.getPoints());

        EvaluationInstrument instrument = evaluationInstrumentRepository.findById(dto.getEvaluationInstrument().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationInstrument ID"));
        entity.setEvaluationInstrument(instrument);

        EvaluationType type = evaluationTypeRepository.findById(dto.getEvaluationType().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationType ID"));
        entity.setEvaluationType(type);

        CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealization().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid CourseRealization ID"));
        entity.setCourseRealization(realization);

        List<LearningOutcome> outcomes = dto.getLearningOutcomes().stream()
                .map(lo -> learningOutcomeRepository.findById(lo.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid LearningOutcome ID: " + lo.getId())))
                .collect(Collectors.toList());
        entity.setLearningOutcomes(outcomes);
    }
}
