package lmsprojekat.service.universityservice;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.universitydto.UniversityDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.repository.AddressRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.universityrepo.UniversityRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class UniversityService extends AbstractCrudService<UniversityDTO, University, Long> {

	private final UniversityRepository universityRepository;
	private final FacultyRepository facultyRepository;
	private final AddressRepository addressRepository;

	public UniversityService(UniversityRepository universityRepository, FacultyRepository facultyRepository,
			AddressRepository addressRepository) {
		this.universityRepository = universityRepository;
		this.facultyRepository = facultyRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	protected SoftDeleteRepository<University, Long> getRepository() {
		return universityRepository;
	}

	@Override
	protected UniversityDTO toDTO(University entity) {
		List<Long> facultyIds = entity.getFaculties() != null
				? entity.getFaculties().stream().map(Faculty::getId).toList()
				: List.of();

		List<Long> addressIds = entity.getAddresses() != null
				? entity.getAddresses().stream().map(Address::getId).toList()
				: List.of();

		return new UniversityDTO(entity.getId(), entity.getName(), entity.getEstablishmentDate(), facultyIds,
				addressIds);
	}

	@Override
	protected University toEntity(UniversityDTO dto) {
		List<Faculty> faculties = dto.getFacultyIds() != null ? dto.getFacultyIds().stream()
				.map(id -> facultyRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + id)))
				.toList() : List.of();

		List<Address> addresses = dto.getAddressIds() != null ? dto.getAddressIds().stream()
				.map(id -> addressRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id)))
				.toList() : List.of();

		return new University(dto.getId(), dto.getName(), dto.getEstablishmentDate(), faculties, addresses);
	}

	@Override
	protected void updateEntity(University entity, UniversityDTO dto) {
		entity.setName(dto.getName());
		entity.setEstablishmentDate(dto.getEstablishmentDate());

		if (dto.getFacultyIds() != null) {
			List<Faculty> faculties = dto.getFacultyIds().stream()
					.map(id -> facultyRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + id)))
					.toList();
			entity.setFaculties(faculties);
		}

		if (dto.getAddressIds() != null) {
			List<Address> addresses = dto.getAddressIds().stream()
					.map(id -> addressRepository.findById(id)
							.orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id)))
					.toList();
			entity.setAddresses(addresses);
		}
	}
}
