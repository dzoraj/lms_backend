package lmsprojekat.service.titleservice;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.titledto.TitleDTO;
import lmsprojekat.dto.titledto.TitleTypeDTO;
import lmsprojekat.model.title.Title;
import lmsprojekat.model.title.TitleType;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.titlerepo.TitleRepository;
import lmsprojekat.repository.titlerepo.TitleTypeRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TitleTypeService extends AbstractCrudService<TitleTypeDTO, TitleType, Long> {

    private final TitleTypeRepository titleTypeRepository;
    private final TitleRepository titleRepository;

    public TitleTypeService(TitleTypeRepository titleTypeRepository, TitleRepository titleRepository) {
        this.titleTypeRepository = titleTypeRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    protected SoftDeleteRepository<TitleType, Long> getRepository() {
        return titleTypeRepository;
    }

    @Override
    protected TitleTypeDTO toDTO(TitleType entity) {
        Title title = entity.getTitle();

        TitleDTO titleDTO = null;
        if (title != null) {
            titleDTO = new TitleDTO();
            titleDTO.setId(title.getId());
            titleDTO.setSelectionDate(title.getSelectionDate());
            titleDTO.setEndDate(title.getEndDate());
        }

        return new TitleTypeDTO(
                entity.getId(),
                entity.getName(),
                titleDTO
        );
    }

    @Override
    protected TitleType toEntity(TitleTypeDTO dto) {
        Title title = null;

        if (dto.getTitle() != null && dto.getTitle().getId() != null) {
            title = titleRepository.findById(dto.getTitle().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Title not found with id: " + dto.getTitle().getId()));
        }

        return new TitleType(
                dto.getId(),
                dto.getName(),
                title
        );
    }

    @Override
    protected void updateEntity(TitleType entity, TitleTypeDTO dto) {
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
