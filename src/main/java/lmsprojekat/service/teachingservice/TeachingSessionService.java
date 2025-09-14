package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.model.teaching.TeachingType;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.teachingrepo.TeachingSessionRepository;
import lmsprojekat.repository.teachingrepo.TeachingTypeRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TeachingSessionService extends AbstractCrudService<TeachingSessionDTO, TeachingSession, Long> {

	private final TeachingSessionRepository teachingSessionRepository;
	private final CourseRealizationRepository courseRealizationRepository;
	private final TeachingTypeRepository teachingTypeRepository;
	private final LearningOutcomeRepository learningOutcomeRepository;

	public TeachingSessionService(TeachingSessionRepository teachingSessionRepository,
			CourseRealizationRepository courseRealizationRepository, TeachingTypeRepository teachingTypeRepository,
			LearningOutcomeRepository learningOutcomeRepository) {
		this.teachingSessionRepository = teachingSessionRepository;
		this.courseRealizationRepository = courseRealizationRepository;
		this.teachingTypeRepository = teachingTypeRepository;
		this.learningOutcomeRepository = learningOutcomeRepository;
	}

	@Override
	protected TeachingSessionRepository getRepository() {
		return teachingSessionRepository;
	}

	@Override
	public TeachingSessionDTO toDTO(TeachingSession entity) {
		if (entity == null)
			return null;

		return new TeachingSessionDTO(entity.getId(), entity.getStartTime(), entity.getEndTime(),
				entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null,
				entity.getTeachingType() != null ? entity.getTeachingType().getId() : null,
				entity.getLearningOutcomes() != null
						? entity.getLearningOutcomes().stream().map(LearningOutcome::getId).collect(Collectors.toList())
						: List.of());
	}

	@Override
	public TeachingSession toEntity(TeachingSessionDTO dto) {
		if (dto == null)
			return null;

		TeachingSession entity = new TeachingSession();
		entity.setId(dto.getId());
		entity.setStartTime(dto.getStartTime());
		entity.setEndTime(dto.getEndTime());

		if (dto.getCourseRealizationId() != null) {
			entity.setCourseRealization(courseRealizationRepository.findById(dto.getCourseRealizationId())
					.orElseThrow(() -> new EntityNotFoundException(
							"CourseRealization not found id=" + dto.getCourseRealizationId())));
		}

		if (dto.getTeachingTypeId() != null) {
			entity.setTeachingType(teachingTypeRepository.findById(dto.getTeachingTypeId()).orElseThrow(
					() -> new EntityNotFoundException("TeachingType not found id=" + dto.getTeachingTypeId())));
		}

		if (dto.getLearningOutcomeIds() != null) {
			List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
					.map(id -> learningOutcomeRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found id=" + id)))
					.collect(Collectors.toList());
			entity.setLearningOutcomes(outcomes);
		}

		return entity;
	}

	@Override
	protected void updateEntity(TeachingSession entity, TeachingSessionDTO dto) {
		if (dto.getStartTime() != null) {
			entity.setStartTime(dto.getStartTime());
		}

		if (dto.getEndTime() != null) {
			entity.setEndTime(dto.getEndTime());
		}

		if (dto.getCourseRealizationId() != null) {
			CourseRealization cr = courseRealizationRepository.findById(dto.getCourseRealizationId())
					.orElseThrow(() -> new EntityNotFoundException(
							"CourseRealization not found id=" + dto.getCourseRealizationId()));
			entity.setCourseRealization(cr);
		}

		if (dto.getTeachingTypeId() != null) {
			TeachingType type = teachingTypeRepository.findById(dto.getTeachingTypeId()).orElseThrow(
					() -> new EntityNotFoundException("TeachingType not found id=" + dto.getTeachingTypeId()));
			entity.setTeachingType(type);
		}

		if (dto.getLearningOutcomeIds() != null) {
			List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
					.map(id -> learningOutcomeRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found id=" + id)))
					.collect(Collectors.toList());
			entity.setLearningOutcomes(outcomes);
		}
	}
}
