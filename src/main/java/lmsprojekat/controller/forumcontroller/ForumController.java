package lmsprojekat.controller.forumcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.forumdto.ForumDTO;
import lmsprojekat.service.forumservice.ForumService;

@RestController
@RequestMapping("/api/forum")
public class ForumController extends BaseCrudController<ForumDTO, Long> {

    private final ForumService service;

    public ForumController(ForumService service) {
        this.service = service;
    }

    @Override
    protected ForumService getService() {
        return service;
    }
}
