package lmsprojekat.service.subjectservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.teaching.EducationalGoal;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.repository.teachingrepo.EducationalGoalRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
@Transactional
public class LearningOutcomeService extends AbstractCrudService<LearningOutcomeDTO, LearningOutcome, Long> {

    private final LearningOutcomeRepository learningOutcomeRepository;
    private final SubjectRepository subjectRepository;
    private final EducationalGoalRepository educationalGoalRepository;

    public LearningOutcomeService(
            LearningOutcomeRepository learningOutcomeRepository,
            SubjectRepository subjectRepository,
            EducationalGoalRepository educationalGoalRepository) {
        this.learningOutcomeRepository = learningOutcomeRepository;
        this.subjectRepository = subjectRepository;
        this.educationalGoalRepository = educationalGoalRepository;
    }

    @Override
    protected SoftDeleteRepository<LearningOutcome, Long> getRepository() {
        return learningOutcomeRepository;
    }

    @Override
    protected LearningOutcomeDTO toDTO(LearningOutcome entity) {
        if (entity == null) return null;

        return new LearningOutcomeDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getSubject() != null ? entity.getSubject().getId() : null,
                entity.getEducationalGoals() != null
                        ? entity.getEducationalGoals().stream().map(EducationalGoal::getId).toList()
                        : List.of(),
                entity.getTeachingMaterials() != null
                        ? entity.getTeachingMaterials().stream().map(tm -> tm.getId()).toList()
                        : List.of(),
                entity.getKnowledgeEvaluations() != null
                        ? entity.getKnowledgeEvaluations().stream().map(ke -> ke.getId()).toList()
                        : List.of(),
                entity.getTeachingSessions() != null
                        ? entity.getTeachingSessions().stream().map(ts -> ts.getId()).toList()
                        : List.of()
        );
    }

    @Override
    protected LearningOutcome toEntity(LearningOutcomeDTO dto) {
        if (dto == null) return null;

        LearningOutcome entity = new LearningOutcome();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found, id=" + dto.getSubjectId()));
            entity.setSubject(subject);
        }

        if (dto.getEducationalGoalIds() != null) {
            List<EducationalGoal> educationalGoals = dto.getEducationalGoalIds().stream()
                    .map(id -> educationalGoalRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("EducationalGoal not found, id=" + id)))
                    .toList();
            entity.setEducationalGoals(educationalGoals);
        } else {
            entity.setEducationalGoals(List.of());
        }

        entity.setTeachingMaterials(List.of());
        entity.setKnowledgeEvaluations(List.of());
        entity.setTeachingSessions(List.of());

        return entity;
    }

    @Override
    protected void updateEntity(LearningOutcome entity, LearningOutcomeDTO dto) {
        entity.setDescription(dto.getDescription());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found, id=" + dto.getSubjectId()));
            entity.setSubject(subject);
        } else {
            entity.setSubject(null);
        }

        if (dto.getEducationalGoalIds() != null) {
            List<EducationalGoal> educationalGoals = dto.getEducationalGoalIds().stream()
                    .map(id -> educationalGoalRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("EducationalGoal not found, id=" + id)))
                    .toList();
            entity.setEducationalGoals(educationalGoals);
        } else {
            entity.setEducationalGoals(List.of());
        }
    }
}
