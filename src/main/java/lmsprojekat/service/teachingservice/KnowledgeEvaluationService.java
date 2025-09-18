package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

	private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;
	private final EvaluationInstrumentRepository evaluationInstrumentRepository;
	private final EvaluationTypeRepository evaluationTypeRepository;
	private final CourseRealizationRepository courseRealizationRepository;
	private final LearningOutcomeRepository learningOutcomeRepository;

	public KnowledgeEvaluationService(KnowledgeEvaluationRepository knowledgeEvaluationRepository,
			EvaluationInstrumentRepository evaluationInstrumentRepository,
			EvaluationTypeRepository evaluationTypeRepository, CourseRealizationRepository courseRealizationRepository,
			LearningOutcomeRepository learningOutcomeRepository) {
		this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
		this.evaluationInstrumentRepository = evaluationInstrumentRepository;
		this.evaluationTypeRepository = evaluationTypeRepository;
		this.courseRealizationRepository = courseRealizationRepository;
		this.learningOutcomeRepository = learningOutcomeRepository;
	}

	@Override
	protected SoftDeleteRepository<KnowledgeEvaluation, Long> getRepository() {
		return knowledgeEvaluationRepository;
	}

	@Override
	public KnowledgeEvaluationDTO toDTO(KnowledgeEvaluation entity) {
		KnowledgeEvaluationDTO dto = new KnowledgeEvaluationDTO();
		dto.setId(entity.getId());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setPoints(entity.getPoints());
		dto.setEvaluationInstrumentId(
				entity.getEvaluationInstrument() != null ? entity.getEvaluationInstrument().getId() : null);
		dto.setEvaluationTypeId(entity.getEvaluationType() != null ? entity.getEvaluationType().getId() : null);
		dto.setCourseRealizationId(
				entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null);
		dto.setLearningOutcomeIds(entity.getLearningOutcomes() != null
				? entity.getLearningOutcomes().stream().map(LearningOutcome::getId).collect(Collectors.toList())
				: List.of());
		return dto;
	}

	@Override
	public KnowledgeEvaluation toEntity(KnowledgeEvaluationDTO dto) {
		KnowledgeEvaluation entity = new KnowledgeEvaluation();
		entity.setId(dto.getId());
		entity.setStartTime(dto.getStartTime());
		entity.setEndTime(dto.getEndTime());
		entity.setPoints(dto.getPoints());

		if (dto.getEvaluationInstrumentId() != null) {
			EvaluationInstrument instrument = evaluationInstrumentRepository.findById(dto.getEvaluationInstrumentId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationInstrument ID"));
			entity.setEvaluationInstrument(instrument);
		}

		if (dto.getEvaluationTypeId() != null) {
			EvaluationType type = evaluationTypeRepository.findById(dto.getEvaluationTypeId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationType ID"));
			entity.setEvaluationType(type);
		}

		if (dto.getCourseRealizationId() != null) {
			CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid CourseRealization ID"));
			entity.setCourseRealization(realization);
		}

		if (dto.getLearningOutcomeIds() != null) {
			List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
					.map(id -> learningOutcomeRepository.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Invalid LearningOutcome ID: " + id)))
					.collect(Collectors.toList());
			entity.setLearningOutcomes(outcomes);
		}

		return entity;
	}

	@Override
	protected void updateEntity(KnowledgeEvaluation entity, KnowledgeEvaluationDTO dto) {
		if (dto.getStartTime() != null)
			entity.setStartTime(dto.getStartTime());
		if (dto.getEndTime() != null)
			entity.setEndTime(dto.getEndTime());
		if (dto.getPoints() != null)
			entity.setPoints(dto.getPoints());

		if (dto.getEvaluationInstrumentId() != null) {
			EvaluationInstrument instrument = evaluationInstrumentRepository.findById(dto.getEvaluationInstrumentId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationInstrument ID"));
			entity.setEvaluationInstrument(instrument);
		}

		if (dto.getEvaluationTypeId() != null) {
			EvaluationType type = evaluationTypeRepository.findById(dto.getEvaluationTypeId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid EvaluationType ID"));
			entity.setEvaluationType(type);
		}

		if (dto.getCourseRealizationId() != null) {
			CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid CourseRealization ID"));
			entity.setCourseRealization(realization);
		}

		if (dto.getLearningOutcomeIds() != null) {
			List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
					.map(id -> learningOutcomeRepository.findById(id)
							.orElseThrow(() -> new IllegalArgumentException("Invalid LearningOutcome ID: " + id)))
					.collect(Collectors.toList());
			entity.setLearningOutcomes(outcomes);
		}
	}
    public List<KnowledgeEvaluationDTO> findBySubject(Long subjectId) {
        return knowledgeEvaluationRepository.findBySubjectId(subjectId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
}
