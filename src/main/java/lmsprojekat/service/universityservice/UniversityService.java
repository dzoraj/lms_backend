package lmsprojekat.service.universityservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.universitydto.FacultyDTO;
import lmsprojekat.dto.universitydto.UniversityDTO;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.universityrepo.UniversityRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class UniversityService extends AbstractCrudService<UniversityDTO, University, Long> {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    public UniversityService(UniversityRepository universityRepository, FacultyRepository facultyRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    protected SoftDeleteRepository<University, Long> getRepository() {
        return universityRepository;
    }

    @Override
    protected UniversityDTO toDTO(University entity) {
        List<FacultyDTO> facultyDTOs = entity.getFaculties() != null
                ? entity.getFaculties().stream().map(faculty -> {
            FacultyDTO dto = new FacultyDTO();
            dto.setId(faculty.getId());
            dto.setName(faculty.getName());
            return dto;
        }).collect(Collectors.toList()) : null;

        return new UniversityDTO(
                entity.getId(),
                entity.getName(),
                entity.getEstablishmentDate(),
                facultyDTOs,
                entity.getAddresses()
        );
    }

    @Override
    protected University toEntity(UniversityDTO dto) {
        List<Faculty> faculties = null;
        if (dto.getFaculties() != null) {
            faculties = dto.getFaculties().stream().map(facultyDTO -> {
                if (facultyDTO.getId() == null) {
                    throw new IllegalArgumentException("Faculty ID cannot be null when converting UniversityDTO to University entity.");
                }
                return facultyRepository.findById(facultyDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + facultyDTO.getId()));
            }).collect(Collectors.toList());
        }

        return new University(
                dto.getId(),
                dto.getName(),
                dto.getEstablishmentDate(),
                faculties,
                dto.getAddresses()
        );
    }

    @Override
    protected void updateEntity(University entity, UniversityDTO dto) {
        entity.setName(dto.getName());
        entity.setEstablishmentDate(dto.getEstablishmentDate());

        if (dto.getFaculties() != null) {
            List<Faculty> faculties = dto.getFaculties().stream().map(facultyDTO -> {
                if (facultyDTO.getId() == null) {
                    throw new IllegalArgumentException("Faculty ID cannot be null for update.");
                }
                return facultyRepository.findById(facultyDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + facultyDTO.getId()));
            }).collect(Collectors.toList());
            entity.setFaculties(faculties);
        }

        entity.setAddresses(dto.getAddresses());
    }
}
