package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.service.userservice.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends BaseCrudController<TeacherDTO, Long> {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @Override
    protected TeacherService getService() {
        return service;
    }

}
