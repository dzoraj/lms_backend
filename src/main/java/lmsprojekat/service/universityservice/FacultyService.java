package lmsprojekat.service.universityservice;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.universitydto.FacultyDTO;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.universityrepo.UniversityRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.AddressService;

@Service
public class FacultyService extends AbstractCrudService<FacultyDTO, Faculty, Long> {

    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;
    private final UniversityRepository universityRepository;
    private final AddressService addressService;

    public FacultyService(FacultyRepository facultyRepository,
                          TeacherRepository teacherRepository,
                          UniversityRepository universityRepository,
                          AddressService addressService) {
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
        this.universityRepository = universityRepository;
        this.addressService = addressService;
    }

    @Override
    protected SoftDeleteRepository<Faculty, Long> getRepository() {
        return facultyRepository;
    }


    @Override
    public FacultyDTO toDTO(Faculty entity) {
        Long deanId = entity.getDean() != null ? entity.getDean().getId() : null;
        Long universityId = entity.getUniversity() != null ? entity.getUniversity().getId() : null;

        return new FacultyDTO(
            entity.getId(),
            entity.getName(),
            deanId,
            universityId,
            entity.getAddresses() != null
                ? entity.getAddresses().stream()
                    .map(addressService::toDTO)
                    .toList()
                : null
        );
    }

    @Override
    public Faculty toEntity(FacultyDTO dto) {
        if (dto.getDean() == null) {
            throw new IllegalArgumentException("Faculty must have a dean id");
        }

        Teacher dean = teacherRepository.findById(dto.getDean())
                .orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDean()));

        University university = null;
        if (dto.getUniversity() != null) {
            university = universityRepository.findById(dto.getUniversity())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversity()));
        }

        return new Faculty(
            dto.getId(),
            dto.getName(),
            dean,
            university,
            dto.getAddresses() != null
                ? dto.getAddresses().stream()
                    .map(addressService::toEntity)
                    .toList()
                : null
        );
    }

    @Override
    protected void updateEntity(Faculty entity, FacultyDTO dto) {
        entity.setName(dto.getName());

        if (dto.getDean() != null) {
            Teacher dean = teacherRepository.findById(dto.getDean())
                    .orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDean()));
            entity.setDean(dean);
        }

        if (dto.getUniversity() != null) {
            University university = universityRepository.findById(dto.getUniversity())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversity()));
            entity.setUniversity(university);
        }

        if (dto.getAddresses() != null) {
            entity.setAddresses(dto.getAddresses().stream()
                .map(addressService::toEntity)
                .toList());
        }
    }
}
