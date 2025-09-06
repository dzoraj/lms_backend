package lmsprojekat.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.SoftDeleteSpecification;

public abstract class AbstractCrudService<DTO, Entity extends SoftDeletableEntity, ID> implements CrudService<DTO, ID> {

	protected abstract SoftDeleteRepository<Entity, ID> getRepository();


	protected abstract DTO toDTO(Entity entity);

	protected abstract Entity toEntity(DTO dto);

	protected abstract void updateEntity(Entity entity, DTO dto);

	@Override
	public DTO save(DTO dto) {
		Entity entity = toEntity(dto);
		entity = getRepository().save(entity);
		return toDTO(entity);
	}

	@Override
	public DTO findById(ID id) {
		Entity entity = getRepository().findById(id).filter(e -> !e.isDeleted()) 
				.orElseThrow(() -> new EntityNotFoundException("Not found with id: " + id));
		return toDTO(entity);
	}

	@Override
	public List<DTO> findAll() {
	    return getRepository().findAll(SoftDeleteSpecification.notDeleted()).stream()
	        .map(this::toDTO)
	        .collect(Collectors.toList());
	}



	@Override
	public DTO update(ID id, DTO dto) {
		Entity entity = getRepository().findById(id).filter(e -> !e.isDeleted())
				.orElseThrow(() -> new EntityNotFoundException("Not found with id: " + id));
		updateEntity(entity, dto);
		return toDTO(getRepository().save(entity));
	}

	@Override
	public void delete(ID id) {
		Entity entity = getRepository().findById(id).filter(e -> !e.isDeleted())
				.orElseThrow(() -> new EntityNotFoundException("Not found with id: " + id));
		entity.setDeleted(true);
		getRepository().save(entity);
	}
}
