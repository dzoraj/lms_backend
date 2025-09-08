package lmsprojekat.service.userservice;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.dto.studentdto.SubjectStudySummaryDTO;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.dto.userdto.StudentDashboardDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.studentservice.StudentInYearService;
import lmsprojekat.service.subjectservice.CourseAttendanceService;

@Service
public class StudentService extends AbstractCrudService<StudentDTO, Student, Long> {

    private final StudentRepository studentRepository;
    private final CourseAttendanceService courseAttendanceService;
    private final StudentInYearService studentInYearService;
    private final EvaluationAttemptRepository evaluationAttemptRepository;

    public StudentService(
            StudentRepository studentRepository,
            CourseAttendanceService courseAttendanceService,
            StudentInYearService studentInYearService,
            EvaluationAttemptRepository evaluationAttemptRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseAttendanceService = courseAttendanceService;
        this.studentInYearService = studentInYearService;
        this.evaluationAttemptRepository = evaluationAttemptRepository;
    }

    @Override
    protected StudentRepository getRepository() {
        return studentRepository;
    }

    @Override
    protected StudentDTO toDTO(Student student) {
        List<Long> courseAttendanceIds = Optional.ofNullable(student.getCourseAttendances())
                .orElse(List.of())
                .stream()
                .filter(Objects::nonNull)
                .map(ca -> ca.getId())
                .collect(Collectors.toList());

        List<Long> studentInYearIds = Optional.ofNullable(student.getStudentInYear())
                .orElse(List.of())
                .stream()
                .filter(Objects::nonNull)
                .map(siy -> siy.getId())
                .collect(Collectors.toList());

        Long addressId = student.getAddress() != null ? student.getAddress().getId() : null;

        List<String> roleNames = Optional.ofNullable(student.getRoles())
                .orElse(List.of())
                .stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getJmbg(),
                student.getEmail(),
                roleNames,
                List.of(),
                courseAttendanceIds,
                studentInYearIds,
                addressId
        );
    }

    @Override
    protected Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setJmbg(dto.getJmbg());
        student.setEmail(dto.getEmail());

        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            student.setAddress(address);
        }

        student.setCourseAttendances(null);
        student.setStudentInYear(null);

        return student;
    }

    @Override
    protected void updateEntity(Student student, StudentDTO dto) {
        student.setName(dto.getName());
        student.setJmbg(dto.getJmbg());

        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            student.setAddress(address);
        } else {
            student.setAddress(null);
        }
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        throw new UnsupportedOperationException("Student creation not supported via StudentService.");
    }

    public StudentDashboardDTO getStudentDashboard(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id " + studentId));

        List<CourseAttendanceDTO> allAttendances = courseAttendanceService.findAllByStudentId(studentId);

        List<CourseAttendanceDTO> currentCourses = allAttendances.stream()
                .filter(ca -> ca != null && ca.getKonacnaOcena() == null)
                .collect(Collectors.toList());

        List<StudentInYearDTO> studyHistory = Optional.ofNullable(student.getStudentInYear())
                .orElse(List.of())
                .stream()
                .filter(Objects::nonNull)
                .map(siy -> studentInYearService.findById(siy.getId()))
                .collect(Collectors.toList());

        Map<Long, List<CourseAttendanceDTO>> bySubject = allAttendances.stream()
                .filter(ca -> ca != null
                        && ca.getCourseRealization() != null
                        && ca.getCourseRealization().getSubject() != null
                        && ca.getCourseRealization().getSubject().getId() != null)
                .collect(Collectors.groupingBy(ca -> ca.getCourseRealization().getSubject().getId()));

        List<SubjectStudySummaryDTO> studyHistoryCourses = bySubject.entrySet().stream()
                .map(e -> {
                    Long subjectId = e.getKey();
                    List<CourseAttendanceDTO> list = e.getValue();

                    CourseAttendanceDTO exemplar = list.stream()
                            .filter(ca -> ca.getCourseRealization() != null && ca.getCourseRealization().getSubject() != null)
                            .findFirst().orElse(null);

                    String name = exemplar != null && exemplar.getCourseRealization().getSubject() != null
                            ? exemplar.getCourseRealization().getSubject().getName()
                            : null;
                    Integer espb = exemplar != null && exemplar.getCourseRealization().getSubject() != null
                            ? exemplar.getCourseRealization().getSubject().getEspb()
                            : null;

                    int attempts = list.size();

                    Integer finalGrade = list.stream()
                            .map(CourseAttendanceDTO::getKonacnaOcena)
                            .filter(Objects::nonNull)
                            .reduce((first, second) -> second) // last grade
                            .orElse(null);

                    Integer finalPoints = evaluationAttemptRepository
                            .findPointsByStudentAndSubject(studentId, subjectId)
                            .stream()
                            .findFirst()
                            .orElse(null);

                    return new SubjectStudySummaryDTO(subjectId, name, espb, attempts, finalPoints, finalGrade);
                })
                .collect(Collectors.toList());

        int totalEspb = studyHistoryCourses.stream()
                .filter(s -> s.getFinalGrade() != null && s.getEspb() != null)
                .mapToInt(SubjectStudySummaryDTO::getEspb)
                .sum();

        OptionalDouble optAvg = studyHistoryCourses.stream()
                .filter(s -> s.getFinalGrade() != null)
                .mapToDouble(SubjectStudySummaryDTO::getFinalGrade)
                .average();

        Double averageGrade = optAvg.isPresent() ? optAvg.getAsDouble() : null;

        StudentDashboardDTO dashboard = new StudentDashboardDTO();
        dashboard.setCurrentCourses(currentCourses);
        dashboard.setStudyHistory(studyHistory);
        dashboard.setStudyHistoryCourses(studyHistoryCourses);
        dashboard.setAverageGrade(averageGrade);
        dashboard.setTotalEspb(totalEspb);

        return dashboard;
    }
}
