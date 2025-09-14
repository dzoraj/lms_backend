package lmsprojekat.service.gradingservice;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.gradingdto.GradeBoundaryDTO;
import lmsprojekat.model.grading.GradeBoundary;
import lmsprojekat.model.grading.GradingScheme;
import lmsprojekat.repository.gradingrepo.GradeBoundaryRepository;
import lmsprojekat.repository.gradingrepo.GradingSchemeRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class GradeBoundaryService extends AbstractCrudService<GradeBoundaryDTO, GradeBoundary, Long> {

    private final GradeBoundaryRepository gradeBoundaryRepository;
    private final GradingSchemeRepository gradingSchemeRepository;

    public GradeBoundaryService(GradeBoundaryRepository gradeBoundaryRepository,
                                GradingSchemeRepository gradingSchemeRepository) {
        this.gradeBoundaryRepository = gradeBoundaryRepository;
        this.gradingSchemeRepository = gradingSchemeRepository;
    }

    @Override
    protected GradeBoundaryRepository getRepository() {
        return gradeBoundaryRepository;
    }

    @Override
    public GradeBoundaryDTO toDTO(GradeBoundary entity) {
        if (entity == null) return null;

        return new GradeBoundaryDTO(
                entity.getId(),
                entity.getMinPoints(),
                entity.getGradeValue(),
                entity.getGradingScheme() != null ? entity.getGradingScheme().getId() : null
        );
    }

    @Override
    public GradeBoundary toEntity(GradeBoundaryDTO dto) {
        if (dto == null) return null;

        GradeBoundary boundary = new GradeBoundary();
        boundary.setId(dto.getId());
        boundary.setMinPoints(dto.getMinPoints());
        boundary.setGradeValue(dto.getGradeValue());

        if (dto.getGradingSchemeId() != null) {
            GradingScheme scheme = gradingSchemeRepository.findById(dto.getGradingSchemeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid GradingScheme ID: " + dto.getGradingSchemeId()));
            boundary.setGradingScheme(scheme);
        }

        return boundary;
    }

    @Override
    protected void updateEntity(GradeBoundary entity, GradeBoundaryDTO dto) {
        if (dto.getMinPoints() != null) {
            entity.setMinPoints(dto.getMinPoints());
        }
        if (dto.getGradeValue() != null) {
            entity.setGradeValue(dto.getGradeValue());
        }
        if (dto.getGradingSchemeId() != null) {
            GradingScheme scheme = gradingSchemeRepository.findById(dto.getGradingSchemeId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Invalid GradingScheme ID: " + dto.getGradingSchemeId()));
            entity.setGradingScheme(scheme);
        }
    }
}
