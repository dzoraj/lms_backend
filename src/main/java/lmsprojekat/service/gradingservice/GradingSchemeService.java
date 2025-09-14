package lmsprojekat.service.gradingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.gradingdto.GradeBoundaryDTO;
import lmsprojekat.dto.gradingdto.GradingSchemeDTO;
import lmsprojekat.model.grading.GradingScheme;
import lmsprojekat.repository.gradingrepo.GradingSchemeRepository;
import lmsprojekat.service.AbstractCrudService;
@Service
public class GradingSchemeService extends AbstractCrudService<GradingSchemeDTO, GradingScheme, Long> {

    private final GradingSchemeRepository gradingSchemeRepository;

    public GradingSchemeService(GradingSchemeRepository gradingSchemeRepository) {
        this.gradingSchemeRepository = gradingSchemeRepository;
    }

    @Override
    protected GradingSchemeRepository getRepository() {
        return gradingSchemeRepository;
    }

    @Override
    public GradingSchemeDTO toDTO(GradingScheme entity) {
        if (entity == null) return null;

        List<GradeBoundaryDTO> boundaryDTOs = null;
        if (entity.getGradeBoundaries() != null) {
            boundaryDTOs = entity.getGradeBoundaries().stream()
                    .map(boundary -> new GradeBoundaryDTO(
                            boundary.getId(),
                            boundary.getMinPoints(),
                            boundary.getGradeValue(),
                            null
                    ))
                    .collect(Collectors.toList());
        }

        return new GradingSchemeDTO(
                entity.getId(),
                entity.getTotalPoints(),
                entity.getThreshold(),
                boundaryDTOs
        );
    }

    @Override
    public GradingScheme toEntity(GradingSchemeDTO dto) {
        if (dto == null) return null;

        GradingScheme scheme = new GradingScheme();
        scheme.setId(dto.getId());
        scheme.setTotalPoints(dto.getTotalPoints());
        scheme.setThreshold(dto.getThreshold());
        return scheme;
    }

    @Override
    protected void updateEntity(GradingScheme entity, GradingSchemeDTO dto) {
        if (dto.getTotalPoints() != null) {
            entity.setTotalPoints(dto.getTotalPoints());
        }
        if (dto.getThreshold() != null) {
            entity.setThreshold(dto.getThreshold());
        }
    }
}
