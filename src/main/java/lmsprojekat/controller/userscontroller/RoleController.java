package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.userdto.RoleDTO;
import lmsprojekat.service.userservice.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController extends BaseCrudController<RoleDTO, Long> {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Override
    protected RoleService getService() {
        return service;
    }

}
