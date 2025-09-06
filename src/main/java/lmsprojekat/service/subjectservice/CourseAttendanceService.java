package lmsprojekat.service.subjectservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.CourseAttendanceRepository;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
@Transactional
public class CourseAttendanceService extends AbstractCrudService<CourseAttendanceDTO, CourseAttendance, Long> {

    private final CourseAttendanceRepository courseAttendanceRepository;
    private final CourseRealizationRepository courseRealizationRepository;
    private final StudentRepository studentRepository;

    public CourseAttendanceService(
            CourseAttendanceRepository courseAttendanceRepository,
            CourseRealizationRepository courseRealizationRepository,
            StudentRepository studentRepository
    ) {
        this.courseAttendanceRepository = courseAttendanceRepository;
        this.courseRealizationRepository = courseRealizationRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    protected SoftDeleteRepository<CourseAttendance, Long> getRepository() {
        return courseAttendanceRepository;
    }

    @Override
    protected CourseAttendanceDTO toDTO(CourseAttendance entity) {
        if (entity == null) return null;

        CourseAttendanceDTO dto = new CourseAttendanceDTO();
        dto.setId(entity.getId());
        dto.setKonacnaOcena(entity.getKonacnaOcena());
        dto.setStudentId(entity.getStudent() != null ? entity.getStudent().getId() : null);

        if (entity.getCourseRealization() != null) {
            CourseRealization realization = entity.getCourseRealization();
            CourseRealizationDTO realizationDTO = new CourseRealizationDTO();
            realizationDTO.setId(realization.getId());

            if (realization.getSubject() != null) {
                Subject subject = realization.getSubject();
                SubjectDTO subjectDTO = new SubjectDTO();
                subjectDTO.setId(subject.getId());
                subjectDTO.setName(subject.getName());
                realizationDTO.setSubject(subjectDTO);
            }

            dto.setCourseRealization(realizationDTO);
        }

        return dto;
    }

    @Override
    protected CourseAttendance toEntity(CourseAttendanceDTO dto) {
        if (dto == null) return null;

        CourseAttendance entity = new CourseAttendance();
        entity.setId(dto.getId());
        entity.setKonacnaOcena(dto.getKonacnaOcena());

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found id=" + dto.getStudentId()));
            entity.setStudent(student);
        }

        if (dto.getCourseRealization() != null && dto.getCourseRealization().getId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealization().getId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealization().getId()));
            entity.setCourseRealization(realization);
        }

        return entity;
    }

    @Override
    protected void updateEntity(CourseAttendance entity, CourseAttendanceDTO dto) {
        entity.setKonacnaOcena(dto.getKonacnaOcena());

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student not found id=" + dto.getStudentId()));
            entity.setStudent(student);
        }

        if (dto.getCourseRealization() != null && dto.getCourseRealization().getId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealization().getId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealization().getId()));
            entity.setCourseRealization(realization);
        }
    }
}
