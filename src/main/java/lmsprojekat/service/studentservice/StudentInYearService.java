package lmsprojekat.service.studentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.student.StudyYear;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.studentrepo.StudyYearRepository;
import lmsprojekat.repository.subjectrepo.CourseAttendanceRepository;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class StudentInYearService extends AbstractCrudService<StudentInYearDTO, StudentInYear, Long> {

    private final StudentInYearRepository studentInYearRepository;
    private final StudentRepository studentRepository;
    private final StudyYearRepository studyYearRepository;
    private final CourseAttendanceRepository courseAttendanceRepository;
    private final CourseRealizationRepository courseRealizationRepository;

    public StudentInYearService(
            StudentInYearRepository studentInYearRepository,
            StudentRepository studentRepository,
            StudyYearRepository studyYearRepository,
            CourseAttendanceRepository courseAttendanceRepository,
            CourseRealizationRepository courseRealizationRepository) {
        this.studentInYearRepository = studentInYearRepository;
        this.studentRepository = studentRepository;
        this.studyYearRepository = studyYearRepository;
        this.courseAttendanceRepository = courseAttendanceRepository;
        this.courseRealizationRepository = courseRealizationRepository;
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
        if (dto.getEnrollmentDate() != null) entity.setEnrollmentDate(dto.getEnrollmentDate());
        if (dto.getIndexNumber() != null) entity.setIndexNumber(dto.getIndexNumber());

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

    @Transactional
    @Override
    public StudentInYearDTO save(StudentInYearDTO dto) {
        StudentInYear entity = toEntity(dto);
        StudentInYear saved = studentInYearRepository.save(entity);
        ensureCourseAttendances(saved);
        return toDTO(saved);
    }

    @Transactional
    protected void ensureCourseAttendances(StudentInYear enrollment) {
        if (enrollment.getStudyYear() == null) return;
        List<Subject> subjects = enrollment.getStudyYear().getSubjects();
        if (subjects == null || subjects.isEmpty()) return;

        Long studentId = enrollment.getStudent().getId();

        for (Subject subject : subjects) {
            if (subject == null || subject.getId() == null) continue;

            CourseAttendance existing = courseAttendanceRepository.findByStudentAndSubject(studentId, subject.getId());
            if (existing != null) continue;

            Optional<CourseRealization> crOpt = courseRealizationRepository.findFirstBySubjectId(subject.getId());
            if (crOpt.isEmpty()) continue;

            CourseAttendance ca = new CourseAttendance();
            ca.setStudent(enrollment.getStudent());
            ca.setCourseRealization(crOpt.get());
            ca.setDeleted(false);
            courseAttendanceRepository.save(ca);
        }
    }
}
