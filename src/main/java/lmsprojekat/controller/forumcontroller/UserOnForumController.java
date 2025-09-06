package lmsprojekat.controller.forumcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.forumdto.UserOnForumDTO;
import lmsprojekat.service.forumservice.UserOnForumService;

@RestController
@RequestMapping("/api/userOnForum")
public class UserOnForumController extends BaseCrudController<UserOnForumDTO, Long> {

    private final UserOnForumService service;

    public UserOnForumController(UserOnForumService service) {
        this.service = service;
    }

    @Override
    protected UserOnForumService getService() {
        return service;
    }
}
