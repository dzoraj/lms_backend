package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.dto.userdto.StudentDashboardDTO;
import lmsprojekat.service.userservice.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends BaseCrudController<StudentDTO, Long> {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Override
    protected StudentService getService() {
        return service;
    }

    @GetMapping("/dashboard/{id}")
    public StudentDashboardDTO getDashboard(@PathVariable Long id) {
        return service.getStudentDashboard(id);
    }
}
