package lmsprojekat.controller.gradingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.gradingdto.GradeBoundaryDTO;
import lmsprojekat.service.gradingservice.GradeBoundaryService;

@RestController
@RequestMapping("/api/gradeBoundary")
public class GradeBoundaryController extends BaseCrudController<GradeBoundaryDTO, Long> {

    private final GradeBoundaryService service;

    public GradeBoundaryController(GradeBoundaryService service) {
        this.service = service;
    }

    @Override
    protected GradeBoundaryService getService() {
        return service;
    }
}