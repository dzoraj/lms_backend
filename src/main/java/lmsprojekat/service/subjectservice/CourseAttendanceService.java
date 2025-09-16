package lmsprojekat.service.subjectservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.subject.CourseRealization;
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

    public CourseAttendanceService(CourseAttendanceRepository courseAttendanceRepository,
                                   CourseRealizationRepository courseRealizationRepository,
                                   StudentRepository studentRepository) {
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
        dto.setCourseRealizationId(entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null);
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

        if (dto.getCourseRealizationId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealizationId()));
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

        if (dto.getCourseRealizationId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealizationId()));
            entity.setCourseRealization(realization);
        }
    }

    public List<CourseAttendanceDTO> findAllByStudentId(Long studentId) {
        return courseAttendanceRepository.findAllByStudentIdWithRealizationAndSubject(studentId).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public void createOrUpdateFinalGrade(Long studentId, Long subjectId, int grade) {
        CourseAttendance ca = courseAttendanceRepository.findByStudentAndSubject(studentId, subjectId);

        if (ca == null) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student not found id=" + studentId));

            CourseRealization realization = courseRealizationRepository.findFirstBySubjectId(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException("No CourseRealization found for subject=" + subjectId));

            ca = new CourseAttendance();
            ca.setStudent(student);
            ca.setCourseRealization(realization);
        }

        ca.setKonacnaOcena(grade);
        courseAttendanceRepository.save(ca);
    }
}
