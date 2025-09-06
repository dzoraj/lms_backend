package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.RegisteredUserDTO;
import lmsprojekat.service.userservice.RegisteredUserService;

@RestController
@RequestMapping("/api/registeredUser")
public class RegisteredUserController extends BaseCrudController<RegisteredUserDTO, Long> {

    private final RegisteredUserService service;

    public RegisteredUserController(RegisteredUserService service) {
        this.service = service;
    }

    @Override
    protected RegisteredUserService getService() {
        return service;
    }
}
