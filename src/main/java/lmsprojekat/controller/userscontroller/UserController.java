package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.UserRequestDTO;
import lmsprojekat.service.userservice.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseCrudController<UserRequestDTO, Long> {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    protected UserService getService() {
        return service;
    }
}
