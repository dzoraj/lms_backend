package lmsprojekat.service.universityservice;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.universitydto.FacultyDTO;
import lmsprojekat.dto.universitydto.UniversityDTO;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.model.users.Teacher;
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

    public FacultyService(FacultyRepository facultyRepository, TeacherRepository teacherRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
        this.universityRepository = universityRepository;
    }

    @Override
    protected SoftDeleteRepository<Faculty, Long> getRepository() {
        return facultyRepository;
    }

    @Override
    protected FacultyDTO toDTO(Faculty entity) {
        Teacher dean = entity.getDean();
        TeacherDTO deanDTO = null;
        if (dean != null) {
            deanDTO = new TeacherDTO();
            deanDTO.setId(dean.getId());
            deanDTO.setName(dean.getName());
            deanDTO.setJmbg(dean.getJmbg());
            deanDTO.setBiography(dean.getBiography());
        }

        University university = entity.getUniversity();
        UniversityDTO universityDTO = null;
        if (university != null) {
            universityDTO = new UniversityDTO();
            universityDTO.setId(university.getId());
            universityDTO.setName(university.getName());
            universityDTO.setEstablishmentDate(university.getEstablishmentDate());
        }

        return new FacultyDTO(
                entity.getId(),
                entity.getName(),
                deanDTO,
                universityDTO,
                entity.getAddresses()
        );
    }

    @Override
    protected Faculty toEntity(FacultyDTO dto) {
        Teacher dean = null;
        if (dto.getDean() != null && dto.getDean().getId() != null) {
            dean = teacherRepository.findById(dto.getDean().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDean().getId()));
        }

        University university = null;
        if (dto.getUniversity() != null && dto.getUniversity().getId() != null) {
            university = universityRepository.findById(dto.getUniversity().getId())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversity().getId()));
        }

        return new Faculty(dto.getId(), dto.getName(), dean, university, dto.getAddresses());
    }

    @Override
    protected void updateEntity(Faculty entity, FacultyDTO dto) {
        entity.setName(dto.getName());

        if (dto.getDean() != null && dto.getDean().getId() != null) {
            Teacher dean = teacherRepository.findById(dto.getDean().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Dean not found with id: " + dto.getDean().getId()));
            entity.setDean(dean);
        }

        if (dto.getUniversity() != null && dto.getUniversity().getId() != null) {
            University university = universityRepository.findById(dto.getUniversity().getId())
                    .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + dto.getUniversity().getId()));
            entity.setUniversity(university);
        }

        entity.setAddresses(dto.getAddresses());
    }
}
