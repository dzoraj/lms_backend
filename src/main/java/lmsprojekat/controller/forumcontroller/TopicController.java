package lmsprojekat.controller.forumcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.forumdto.TopicDTO;
import lmsprojekat.service.forumservice.TopicService;

@RestController
@RequestMapping("/api/topic")
public class TopicController extends BaseCrudController<TopicDTO, Long> {

    private final TopicService service;

    public TopicController(TopicService service) {
        this.service = service;
    }

    @Override
    protected TopicService getService() {
        return service;
    }
}
