package lmsprojekat.controller.forumcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.forumdto.PostDTO;
import lmsprojekat.service.forumservice.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController extends BaseCrudController<PostDTO, Long> {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @Override
    protected PostService getService() {
        return service;
    }
}
