package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.teachingrepo.TeachingMaterialRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
@Transactional
public class TeachingMaterialService extends AbstractCrudService<TeachingMaterialDTO, TeachingMaterial, Long> {

    private final TeachingMaterialRepository teachingMaterialRepository;
    private final LearningOutcomeRepository learningOutcomeRepository;
    public TeachingMaterialService(
            TeachingMaterialRepository teachingMaterialRepository,
            LearningOutcomeRepository learningOutcomeRepository) {
        this.teachingMaterialRepository = teachingMaterialRepository;
        this.learningOutcomeRepository = learningOutcomeRepository;
    }

    @Override
    protected SoftDeleteRepository<TeachingMaterial, Long> getRepository() {
        return teachingMaterialRepository;
    }

    @Override
    protected TeachingMaterialDTO toDTO(TeachingMaterial entity) {
        if (entity == null) return null;

        TeachingMaterialDTO dto = new TeachingMaterialDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthors(entity.getAuthors());
        dto.setYearOfPublication(entity.getYearOfPublication());
        dto.setLearningOutcomeId(entity.getLearningOutcome() != null ? entity.getLearningOutcome().getId() : null);

        dto.setFileIds(entity.getFiles() != null
                ? entity.getFiles().stream().map(File::getId).collect(Collectors.toList())
                : List.of());

        return dto;
    }

    @Override
    protected TeachingMaterial toEntity(TeachingMaterialDTO dto) {
        if (dto == null) return null;

        TeachingMaterial entity = new TeachingMaterial();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthors(dto.getAuthors());
        entity.setYearOfPublication(dto.getYearOfPublication());

        if (dto.getLearningOutcomeId() != null) {
            LearningOutcome learningOutcome = learningOutcomeRepository.findById(dto.getLearningOutcomeId())
                    .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found with id=" + dto.getLearningOutcomeId()));
            entity.setLearningOutcome(learningOutcome);
        } else {
            entity.setLearningOutcome(null);
        }

        entity.setFiles(List.of());

        return entity;
    }

    @Override
    protected void updateEntity(TeachingMaterial entity, TeachingMaterialDTO dto) {
        entity.setName(dto.getName());
        entity.setAuthors(dto.getAuthors());
        entity.setYearOfPublication(dto.getYearOfPublication());

        if (dto.getLearningOutcomeId() != null) {
            LearningOutcome learningOutcome = learningOutcomeRepository.findById(dto.getLearningOutcomeId())
                    .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found with id=" + dto.getLearningOutcomeId()));
            entity.setLearningOutcome(learningOutcome);
        } else {
            entity.setLearningOutcome(null);
        }


    }
}
