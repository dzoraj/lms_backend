package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.model.users.Student;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class StudentService extends AbstractCrudService<StudentDTO, Student, Long> {
	
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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

        // UserOnForum not included ----
        List<Long> userOnForumIds = List.of();

        return new StudentDTO(
            student.getId(),
            student.getEmail(),
            roleNames,
            userOnForumIds,
            student.getName(),
            student.getJmbg(),
            courseAttendanceIds,
            studentInYearIds,
            addressId
        );
    }

    @Override
    protected Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setEmail(dto.getEmail());

        student.setName(dto.getName());
        student.setJmbg(dto.getJmbg());

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
}
