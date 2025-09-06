package lmsprojekat.service.subjectservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.dto.teachingdto.EducationalGoalDTO;
import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.teachingdto.EvaluationTypeDTO;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;
import lmsprojekat.dto.teachingdto.TeachingTypeDTO;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.teaching.EducationalGoal;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.model.teaching.TeachingSession;
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

        LearningOutcomeDTO dto = new LearningOutcomeDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());

        if (entity.getSubject() != null) {
            SubjectDTO subjectDTO = new SubjectDTO();
            subjectDTO.setId(entity.getSubject().getId());
            subjectDTO.setName(entity.getSubject().getName());
            dto.setSubject(subjectDTO);
        }

        dto.setEducationalGoals(entity.getEducationalGoals() != null
                ? entity.getEducationalGoals().stream().map(this::educationalGoalToDTO).toList()
                : List.of());

        dto.setTeachingMaterials(entity.getTeachingMaterials() != null
                ? entity.getTeachingMaterials().stream().map(this::teachingMaterialToDTO).toList()
                : List.of());

        dto.setKnowledgeEvaluations(entity.getKnowledgeEvaluations() != null
                ? entity.getKnowledgeEvaluations().stream().map(this::knowledgeEvaluationToDTO).toList()
                : List.of());

        dto.setTeachingSessions(entity.getTeachingSessions() != null
                ? entity.getTeachingSessions().stream().map(this::teachingSessionToDTO).toList()
                : List.of());

        return dto;
    }

    @Override
    protected LearningOutcome toEntity(LearningOutcomeDTO dto) {
        if (dto == null) return null;

        LearningOutcome entity = new LearningOutcome();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());

        if (dto.getSubject() != null && dto.getSubject().getId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found, id=" + dto.getSubject().getId()));
            entity.setSubject(subject);
        }

        entity.setEducationalGoals(dto.getEducationalGoals() != null
                ? dto.getEducationalGoals().stream().map(egDto -> {
                    if (egDto.getId() == null)
                        throw new IllegalArgumentException("EducationalGoal id is required");
                    return educationalGoalRepository.findById(egDto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("EducationalGoal not found, id=" + egDto.getId()));
                }).toList()
                : List.of());

        entity.setTeachingMaterials(List.of()); 
        entity.setKnowledgeEvaluations(List.of());
        entity.setTeachingSessions(List.of());

        return entity;
    }

    @Override
    protected void updateEntity(LearningOutcome entity, LearningOutcomeDTO dto) {
        entity.setDescription(dto.getDescription());

        if (dto.getSubject() != null && dto.getSubject().getId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found, id=" + dto.getSubject().getId()));
            entity.setSubject(subject);
        } else {
            entity.setSubject(null);
        }

        if (dto.getEducationalGoals() != null) {
            List<EducationalGoal> educationalGoals = dto.getEducationalGoals().stream()
                    .map(egDto -> {
                        if (egDto.getId() == null)
                            throw new IllegalArgumentException("EducationalGoal id is required");
                        return educationalGoalRepository.findById(egDto.getId())
                                .orElseThrow(() -> new EntityNotFoundException("EducationalGoal not found, id=" + egDto.getId()));
                    }).toList();
            entity.setEducationalGoals(educationalGoals);
        } else {
            entity.setEducationalGoals(List.of());
        }

    }

    private EducationalGoalDTO educationalGoalToDTO(EducationalGoal entity) {
        if (entity == null) return null;
        EducationalGoalDTO dto = new EducationalGoalDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setLearningOutcomes(null); 
        return dto;
    }

    private TeachingMaterialDTO teachingMaterialToDTO(TeachingMaterial entity) {
        if (entity == null) return null;
        TeachingMaterialDTO dto = new TeachingMaterialDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthors(entity.getAuthors());
        dto.setYearOfPublication(entity.getYearOfPublication());
        dto.setLearningOutcomeId(entity.getLearningOutcome() != null ? entity.getLearningOutcome().getId() : null);
        dto.setFileIds(entity.getFiles() != null
                ? entity.getFiles().stream().map(file -> file.getId()).toList()
                : List.of());
        return dto;
    }

    private KnowledgeEvaluationDTO knowledgeEvaluationToDTO(KnowledgeEvaluation entity) {
        if (entity == null) return null;
        KnowledgeEvaluationDTO dto = new KnowledgeEvaluationDTO();
        dto.setId(entity.getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setPoints(entity.getPoints());

        if (entity.getEvaluationInstrument() != null) {
            EvaluationInstrumentDTO instrumentDTO = new EvaluationInstrumentDTO();
            instrumentDTO.setId(entity.getEvaluationInstrument().getId());
            instrumentDTO.setName(entity.getEvaluationInstrument().getName());
            dto.setEvaluationInstrument(instrumentDTO);
        }

        if (entity.getEvaluationType() != null) {
            EvaluationTypeDTO typeDTO = new EvaluationTypeDTO();
            typeDTO.setId(entity.getEvaluationType().getId());
            typeDTO.setName(entity.getEvaluationType().getName());
            dto.setEvaluationType(typeDTO);
        }

        dto.setCourseRealization(entity.getCourseRealization());
        dto.setLearningOutcomes(null);

        return dto;
    }

    private TeachingSessionDTO teachingSessionToDTO(TeachingSession entity) {
        if (entity == null) return null;
        TeachingSessionDTO dto = new TeachingSessionDTO();
        dto.setId(entity.getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());

        dto.setCourseRealizationId(entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null);

        if (entity.getTeachingType() != null) {
            TeachingTypeDTO typeDTO = new TeachingTypeDTO();
            typeDTO.setId(entity.getTeachingType().getId());
            typeDTO.setName(entity.getTeachingType().getName());
            dto.setTeachingType(typeDTO);
        }

        dto.setLearningOutcomeIds(entity.getLearningOutcomes() != null
                ? entity.getLearningOutcomes().stream().map(LearningOutcome::getId).toList()
                : List.of());

        return dto;
    }
}
