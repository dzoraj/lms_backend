package lmsprojekat.controller.gradingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.gradingdto.GradingSchemeDTO;
import lmsprojekat.service.gradingservice.GradingSchemeService;

@RestController
@RequestMapping("/api/gradingScheme")
public class GradingSchemeController extends BaseCrudController<GradingSchemeDTO, Long> {

    private final GradingSchemeService service;

    public GradingSchemeController(GradingSchemeService service) {
        this.service = service;
    }

    @Override
    protected GradingSchemeService getService() {
        return service;
    }
}