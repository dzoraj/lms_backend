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
        Long deanId = null;
        if (entity.getDean() != null) {
            deanId = entity.getDean().getId();
        }

        Long universityId = null;
        if (entity.getUniversity() != null) {
            universityId = entity.getUniversity().getId();
        }

        return new FacultyDTO(
            entity.getId(),
            entity.getName(),
            deanId,
            universityId,
            entity.getAddresses()
        );
    }


    @Override
    protected Faculty toEntity(FacultyDTO dto) {
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

        return new Faculty(dto.getId(), dto.getName(), dean, university, dto.getAddresses());
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

        entity.setAddresses(dto.getAddresses());
    }

}
