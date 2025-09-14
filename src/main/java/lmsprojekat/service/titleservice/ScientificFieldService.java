package lmsprojekat.service.titleservice;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.titledto.ScientificFieldDTO;
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

	public ScientificFieldService(ScientificFieldRepository scientificFieldRepository,
			TitleRepository titleRepository) {
		this.scientificFieldRepository = scientificFieldRepository;
		this.titleRepository = titleRepository;
	}

	@Override
	protected SoftDeleteRepository<ScientificField, Long> getRepository() {
		return scientificFieldRepository;
	}

	@Override
	protected ScientificFieldDTO toDTO(ScientificField entity) {
		return new ScientificFieldDTO(entity.getId(), entity.getName(),
				entity.getTitle() != null ? entity.getTitle().getId() : null);
	}

	@Override
	protected ScientificField toEntity(ScientificFieldDTO dto) {
		Title title = null;
		if (dto.getTitleId() != null) {
			title = titleRepository.findById(dto.getTitleId())
					.orElseThrow(() -> new EntityNotFoundException("Title not found id=" + dto.getTitleId()));
		}
		return new ScientificField(dto.getId(), dto.getName(), title);
	}

	@Override
	protected void updateEntity(ScientificField entity, ScientificFieldDTO dto) {
		if (dto.getName() != null) {
			entity.setName(dto.getName());
		}

		if (dto.getTitleId() != null) {
			Title title = titleRepository.findById(dto.getTitleId())
					.orElseThrow(() -> new EntityNotFoundException("Title not found id=" + dto.getTitleId()));
			entity.setTitle(title);
		} else {
			entity.setTitle(null);
		}
	}
}
