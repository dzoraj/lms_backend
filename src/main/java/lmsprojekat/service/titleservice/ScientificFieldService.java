package lmsprojekat.service.titleservice;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.titledto.ScientificFieldDTO;
import lmsprojekat.dto.titledto.TitleDTO;
import lmsprojekat.model.title.ScientificField;
import lmsprojekat.model.title.Title;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.titlerepo.ScientificFieldRepository;
import lmsprojekat.repository.titlerepo.TitleRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class ScientificFieldService extends AbstractCrudService<ScientificFieldDTO, ScientificField, Long> {

    private final ScientificFieldRepository scientificFieldRepository;
    private final TitleRepository titleRepository;

    public ScientificFieldService(ScientificFieldRepository scientificFieldRepository, TitleRepository titleRepository) {
        this.scientificFieldRepository = scientificFieldRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    protected SoftDeleteRepository<ScientificField, Long> getRepository() {
        return scientificFieldRepository;
    }

    @Override
    protected ScientificFieldDTO toDTO(ScientificField entity) {
        Title title = entity.getTitle();
        TitleDTO titleDTO = null;
        if (title != null) {
            titleDTO = new TitleDTO();
            titleDTO.setId(title.getId());
            titleDTO.setSelectionDate(title.getSelectionDate());
            titleDTO.setEndDate(title.getEndDate());
        }

        return new ScientificFieldDTO(entity.getId(), entity.getName(), titleDTO);
    }

    @Override
    protected ScientificField toEntity(ScientificFieldDTO dto) {
        Title title = null;
        if (dto.getTitle() != null && dto.getTitle().getId() != null) {
            title = titleRepository.findById(dto.getTitle().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Title not found with id: " + dto.getTitle().getId()));
        }

        return new ScientificField(dto.getId(), dto.getName(), title);
    }

    @Override
    protected void updateEntity(ScientificField entity, ScientificFieldDTO dto) {
        entity.setName(dto.getName());

        if (dto.getTitle() != null && dto.getTitle().getId() != null) {
            Title title = titleRepository.findById(dto.getTitle().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Title not found with id: " + dto.getTitle().getId()));
            entity.setTitle(title);
        } else {
            entity.setTitle(null);
        }
    }
}
