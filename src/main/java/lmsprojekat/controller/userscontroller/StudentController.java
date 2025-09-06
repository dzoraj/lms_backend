package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.service.userservice.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController extends BaseCrudController<StudentDTO, Long> {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Override
    protected StudentService getService() {
        return service;
    }
}
