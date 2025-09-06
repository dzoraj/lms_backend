package lmsprojekat.service.teachingservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
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

        EducationalGoalDTO dto = new EducationalGoalDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());

        if (entity.getLearningOutcomes() != null) {
            List<LearningOutcomeDTO> learningOutcomeDTOs = entity.getLearningOutcomes().stream()
                .map(lo -> {
                    LearningOutcomeDTO loDto = new LearningOutcomeDTO();
                    loDto.setId(lo.getId());
                    loDto.setDescription(lo.getDescription());
                    loDto.setSubject(null); 
                    loDto.setEducationalGoals(null);
                    loDto.setTeachingMaterials(null);
                    loDto.setKnowledgeEvaluations(null);
                    loDto.setTeachingSessions(null);
                    return loDto;
                }).toList();
            dto.setLearningOutcomes(learningOutcomeDTOs);
        } else {
            dto.setLearningOutcomes(List.of());
        }

        return dto;
    }

    @Override
    protected EducationalGoal toEntity(EducationalGoalDTO dto) {
        if (dto == null) return null;

        EducationalGoal entity = new EducationalGoal();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

        if (dto.getLearningOutcomes() != null) {
            List<LearningOutcome> learningOutcomes = dto.getLearningOutcomes().stream()
                .map(loDto -> {
                    if (loDto.getId() == null) {
                        throw new IllegalArgumentException("LearningOutcome id is required");
                    }
                    return learningOutcomeRepository.findById(loDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found, id=" + loDto.getId()));
                }).toList();
            entity.setLearningOutcomes(learningOutcomes);
        } else {
            entity.setLearningOutcomes(List.of());
        }

        return entity;
    }

    @Override
    protected void updateEntity(EducationalGoal entity, EducationalGoalDTO dto) {
        entity.setDescription(dto.getDescription());

        if (dto.getLearningOutcomes() != null) {
            List<LearningOutcome> learningOutcomes = dto.getLearningOutcomes().stream()
                .map(loDto -> {
                    if (loDto.getId() == null) {
                        throw new IllegalArgumentException("LearningOutcome id is required");
                    }
                    return learningOutcomeRepository.findById(loDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found, id=" + loDto.getId()));
                }).toList();
            entity.setLearningOutcomes(learningOutcomes);
        } else {
            entity.setLearningOutcomes(List.of());
        }
    }
}
