package lmsprojekat.service.universityservice;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.universitydto.FacultyDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.AddressRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.universityrepo.UniversityRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class FacultyService extends AbstractCrudService<FacultyDTO, Faculty, Long> {

	private final FacultyRepository facultyRepository;
	private final TeacherRepository teacherRepository;
	private final UniversityRepository universityRepository;
	private final AddressRepository addressRepository;

	public FacultyService(FacultyRepository facultyRepository, TeacherRepository teacherRepository,
			UniversityRepository universityRepository, AddressRepository addressRepository) {
		this.facultyRepository = facultyRepository;
		this.teacherRepository = teacherRepository;
		this.universityRepository = universityRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	protected SoftDeleteRepository<Faculty, Long> getRepository() {
		return facultyRepository;
	}

	@Override
	public FacultyDTO toDTO(Faculty entity) {
		Long deanId = entity.getDean() != null ? entity.getDean().getId() : null;
		Long universityId = entity.getUniversity() != null ? entity.getUniversity().getId() : null;
		List<Long> addressIds = entity.getAddresses() != null
				? entity.getAddresses().stream().map(Address::getId).toList()
				: List.of();

		return new FacultyDTO(entity.getId(), entity.getName(), deanId, universityId, addressIds);
	}

	@Override
	public Faculty toEntity(FacultyDTO dto) {
		if (dto.getDeanId() == null) {
			throw new IllegalArgumentException("Faculty must have a dean id");
		}

		Teacher dean = teacherRepository.findById(dto.getDeanId())
				.orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDeanId()));

		University university = null;
		if (dto.getUniversityId() != null) {
			university = universityRepository.findById(dto.getUniversityId()).orElseThrow(
					() -> new EntityNotFoundException("University not found with id: " + dto.getUniversityId()));
		}

		List<Address> addresses = dto.getAddressIds() != null ? dto.getAddressIds().stream()
				.map(id -> addressRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id)))
				.toList() : List.of();

		return new Faculty(dto.getId(), dto.getName(), dean, university, addresses);
	}

	@Override
	protected void updateEntity(Faculty entity, FacultyDTO dto) {
		entity.setName(dto.getName());

		if (dto.getDeanId() != null) {
			Teacher dean = teacherRepository.findById(dto.getDeanId())
					.orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDeanId()));
			entity.setDean(dean);
		}

		if (dto.getUniversityId() != null) {
			University university = universityRepository.findById(dto.getUniversityId()).orElseThrow(
					() -> new EntityNotFoundException("University not found with id: " + dto.getUniversityId()));
			entity.setUniversity(university);
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
