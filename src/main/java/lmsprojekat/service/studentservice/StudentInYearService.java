package lmsprojekat.service.studentservice;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.student.StudyYear;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.studentrepo.StudyYearRepository;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class StudentInYearService extends AbstractCrudService<StudentInYearDTO, StudentInYear, Long> {

    private final StudentInYearRepository studentInYearRepository;
    private final StudentRepository studentRepository;
    private final StudyYearRepository studyYearRepository;

    public StudentInYearService(
        StudentInYearRepository studentInYearRepository,
        StudentRepository studentRepository,
        StudyYearRepository studyYearRepository
    ) {
        this.studentInYearRepository = studentInYearRepository;
        this.studentRepository = studentRepository;
        this.studyYearRepository = studyYearRepository;
    }

    @Override
    protected StudentInYearRepository getRepository() {
        return studentInYearRepository;
    }

    @Override
    protected StudentInYearDTO toDTO(StudentInYear entity) {
        return new StudentInYearDTO(
            entity.getId(),
            entity.getEnrollmentDate(),
            entity.getIndexNumber(),
            entity.getStudent().getId(),
            entity.getStudyYear() != null ? entity.getStudyYear().getId() : null
        );
    }

    @Override
    protected StudentInYear toEntity(StudentInYearDTO dto) {
        StudentInYear entity = new StudentInYear();
        entity.setId(dto.getId());
        entity.setEnrollmentDate(dto.getEnrollmentDate());
        entity.setIndexNumber(dto.getIndexNumber());

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + dto.getStudentId()));
        entity.setStudent(student);

        if (dto.getStudyYearId() != null) {
            StudyYear studyYear = studyYearRepository.findById(dto.getStudyYearId())
                    .orElseThrow(() -> new IllegalArgumentException("StudyYear not found with id " + dto.getStudyYearId()));
            entity.setStudyYear(studyYear);
        }

        return entity;
    }

    @Override
    protected void updateEntity(StudentInYear entity, StudentInYearDTO dto) {
        if (dto.getEnrollmentDate() != null) {
            entity.setEnrollmentDate(dto.getEnrollmentDate());
        }

        if (dto.getIndexNumber() != null) {
            entity.setIndexNumber(dto.getIndexNumber());
        }

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + dto.getStudentId()));
            entity.setStudent(student);
        }

        if (dto.getStudyYearId() != null) {
            StudyYear studyYear = studyYearRepository.findById(dto.getStudyYearId())
                    .orElseThrow(() -> new IllegalArgumentException("StudyYear not found with id " + dto.getStudyYearId()));
            entity.setStudyYear(studyYear);
        }
    }
}
