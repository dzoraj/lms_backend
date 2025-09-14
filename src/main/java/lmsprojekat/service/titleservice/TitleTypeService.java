package lmsprojekat.service.titleservice;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
		return new TitleTypeDTO(entity.getId(), entity.getName(),
				entity.getTitle() != null ? entity.getTitle().getId() : null);
	}

	@Override
	protected TitleType toEntity(TitleTypeDTO dto) {
		Title title = null;
		if (dto.getTitleId() != null) {
			title = titleRepository.findById(dto.getTitleId())
					.orElseThrow(() -> new EntityNotFoundException("Title not found with id: " + dto.getTitleId()));
		}

		return new TitleType(dto.getId(), dto.getName(), title);
	}

	@Override
	protected void updateEntity(TitleType entity, TitleTypeDTO dto) {
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}

		if (dto.getTitleId() != null) {
			Title title = titleRepository.findById(dto.getTitleId())
					.orElseThrow(() -> new EntityNotFoundException("Title not found with id: " + dto.getTitleId()));
			entity.setTitle(title);
		} else {
			entity.setTitle(null);
		}
	}
}
