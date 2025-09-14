package lmsprojekat.service.teachingservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.EducationalGoalDTO;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.teaching.EducationalGoal;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.teachingrepo.EducationalGoalRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
@Transactional
public class EducationalGoalService extends AbstractCrudService<EducationalGoalDTO, EducationalGoal, Long> {

    private final EducationalGoalRepository educationalGoalRepository;
    private final LearningOutcomeRepository learningOutcomeRepository;

    public EducationalGoalService(EducationalGoalRepository educationalGoalRepository,
                                  LearningOutcomeRepository learningOutcomeRepository) {
        this.educationalGoalRepository = educationalGoalRepository;
        this.learningOutcomeRepository = learningOutcomeRepository;
    }

    @Override
    protected SoftDeleteRepository<EducationalGoal, Long> getRepository() {
        return educationalGoalRepository;
    }

    @Override
    protected EducationalGoalDTO toDTO(EducationalGoal entity) {
        if (entity == null) return null;

        return new EducationalGoalDTO(
            entity.getId(),
            entity.getDescription(),
            entity.getLearningOutcomes() != null
                ? entity.getLearningOutcomes().stream().map(LearningOutcome::getId).toList()
                : List.of()
        );
    }

    @Override
    protected EducationalGoal toEntity(EducationalGoalDTO dto) {
        if (dto == null) return null;

        EducationalGoal entity = new EducationalGoal();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

        if (dto.getLearningOutcomeIds() != null) {
            List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
                .map(id -> learningOutcomeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found, id=" + id)))
                .toList();
            entity.setLearningOutcomes(outcomes);
        } else {
            entity.setLearningOutcomes(List.of());
        }

        return entity;
    }

    @Override
    protected void updateEntity(EducationalGoal entity, EducationalGoalDTO dto) {
        entity.setDescription(dto.getDescription());

        if (dto.getLearningOutcomeIds() != null) {
            List<LearningOutcome> outcomes = dto.getLearningOutcomeIds().stream()
                .map(id -> learningOutcomeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found, id=" + id)))
                .toList();
            entity.setLearningOutcomes(outcomes);
        } else {
            entity.setLearningOutcomes(List.of());
        }
    }
}
