package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;
import lmsprojekat.service.teachingservice.TeachingSessionService;

@RestController
@RequestMapping("/api/teachingSession")
public class TeachingSessionController extends BaseCrudController<TeachingSessionDTO, Long> {

    private final TeachingSessionService service;

    public TeachingSessionController(TeachingSessionService service) {
        this.service = service;
    }

    @Override
    protected TeachingSessionService getService() {
        return service;
    }
}
