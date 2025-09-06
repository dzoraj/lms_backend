package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.model.Address;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.model.title.Title;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TeacherService extends AbstractCrudService<TeacherDTO, Teacher, Long> {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    protected TeacherRepository getRepository() {
        return teacherRepository;
    }

    @Override
	public TeacherDTO toDTO(Teacher teacher) {
        List<Long> titleIds = teacher.getTitles() != null
            ? teacher.getTitles().stream().map(Title::getId).collect(Collectors.toList())
            : List.of();

        List<Long> courseIds = teacher.getCourses() != null
            ? teacher.getCourses().stream().map(TeacherOnCourse::getId).collect(Collectors.toList())
            : List.of();

        Long addressId = teacher.getAddress() != null ? teacher.getAddress().getId() : null;

        List<String> roleNames = teacher.getRoles() != null
            ? teacher.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList())
            : List.of();

        List<Long> userOnForumIds = List.of();

        return new TeacherDTO(
            teacher.getId(),
            teacher.getEmail(),
            roleNames,
            userOnForumIds,
            teacher.getName(),
            teacher.getBiography(),
            teacher.getJmbg(),
            titleIds,
            courseIds,
            addressId
        );
    }

    @Override
    protected Teacher toEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setEmail(dto.getEmail());
        teacher.setName(dto.getName());
        teacher.setBiography(dto.getBiography());
        teacher.setJmbg(dto.getJmbg());

        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            teacher.setAddress(address);
        } else {
            teacher.setAddress(null);
        }

        // za sad null ---
        teacher.setTitles(null);
        teacher.setCourses(null);

        return teacher;
    }

    @Override
    protected void updateEntity(Teacher teacher, TeacherDTO dto) {
        teacher.setName(dto.getName());
        teacher.setBiography(dto.getBiography());
        teacher.setJmbg(dto.getJmbg());

        if (dto.getAddressId() != null) {
            Address address = new Address();
            address.setId(dto.getAddressId());
            teacher.setAddress(address);
        } else {
            teacher.setAddress(null);
        }

    }

    @Override
    public TeacherDTO save(TeacherDTO dto) {
        throw new UnsupportedOperationException("Teacher creation is not supported here.");
    }
}
