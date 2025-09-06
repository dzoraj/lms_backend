package lmsprojekat.controller.titlecontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.titledto.ScientificFieldDTO;
import lmsprojekat.service.titleservice.ScientificFieldService;

@RestController
@RequestMapping("/api/scientificField")
public class ScientificFieldController extends BaseCrudController<ScientificFieldDTO, Long> {

    private final ScientificFieldService service;

    public ScientificFieldController(ScientificFieldService service) {
        this.service = service;
    }

    @Override
    protected ScientificFieldService getService() {
        return service;
    }
}
