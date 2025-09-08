package lmsprojekat.service.userservice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.dto.userdto.StudentDashboardDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;


@Service
public class StudentService extends AbstractCrudService<StudentDTO, Student, Long> {

    private final StudentRepository studentRepository;

    private final lmsprojekat.service.subjectservice.CourseAttendanceService courseAttendanceService;
    private final lmsprojekat.service.studentservice.StudentInYearService studentInYearService;

    public StudentService(
        StudentRepository studentRepository,
        lmsprojekat.service.subjectservice.CourseAttendanceService courseAttendanceService,
        lmsprojekat.service.studentservice.StudentInYearService studentInYearService
    ) {
        this.studentRepository = studentRepository;
        this.courseAttendanceService = courseAttendanceService;
        this.studentInYearService = studentInYearService;
    }

    @Override
    protected StudentRepository getRepository() {
        return studentRepository;
    }

    @Override
    protected StudentDTO toDTO(Student student) {
        List<Long> courseAttendanceIds = student.getCourseAttendances() != null
            ? student.getCourseAttendances().stream().map(CourseAttendance::getId).collect(Collectors.toList())
            : List.of();

        List<Long> studentInYearIds = student.getStudentInYear() != null
            ? student.getStudentInYear().stream().map(StudentInYear::getId).collect(Collectors.toList())
            : List.of();

        Long addressId = student.getAddress() != null ? student.getAddress().getId() : null;

        List<String> roleNames = student.getRoles() != null
            ? student.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList())
            : List.of();

        // UserOnForum not included
        List<Long> userOnForumIds = List.of();

        return new StudentDTO(
            student.getId(),
            student.getName(),
            student.getJmbg(),
            student.getEmail(),
            roleNames,
            userOnForumIds,
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
        } else {
            student.setAddress(null);
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

        List<CourseAttendanceDTO> currentCourses = student.getCourseAttendances() == null
            ? List.of()
            : student.getCourseAttendances().stream()
                .filter(Objects::nonNull)
                .map(CourseAttendance::getId)
                .filter(Objects::nonNull)
                .map(id -> courseAttendanceService.findById(id))
                .collect(Collectors.toList());

        List<StudentInYearDTO> studyHistory = student.getStudentInYear() == null
            ? List.of()
            : student.getStudentInYear().stream()
                .filter(Objects::nonNull)
                .map(StudentInYear::getId)
                .filter(Objects::nonNull)
                .map(id -> studentInYearService.findById(id)) 
                .collect(Collectors.toList());

        double sumGrades = 0;
        int gradedCount = 0;
        int totalEspb = 0;

        for (CourseAttendanceDTO ca : currentCourses) {
            if (ca != null && ca.getKonacnaOcena() != null) {
                sumGrades += ca.getKonacnaOcena();
                gradedCount++;
            }
            if (ca != null
                && ca.getCourseRealization() != null
                && ca.getCourseRealization().getSubject() != null
                && ca.getCourseRealization().getSubject().getEspb() != null) {
                totalEspb += ca.getCourseRealization().getSubject().getEspb();
            }
        }

        Double avgGrade = gradedCount > 0 ? (sumGrades / gradedCount) : null;

        StudentDashboardDTO dashboard = new StudentDashboardDTO();
        dashboard.setCurrentCourses(currentCourses);
        dashboard.setStudyHistory(studyHistory);
        dashboard.setAverageGrade(avgGrade);
        dashboard.setTotalEspb(totalEspb);

        return dashboard;
    }
}
