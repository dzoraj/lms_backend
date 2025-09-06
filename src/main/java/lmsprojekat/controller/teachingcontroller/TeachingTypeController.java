package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.TeachingTypeDTO;
import lmsprojekat.service.teachingservice.TeachingTypeService;

@RestController
@RequestMapping("/api/teachingType")
public class TeachingTypeController extends BaseCrudController<TeachingTypeDTO, Long> {

    private final TeachingTypeService service;

    public TeachingTypeController(TeachingTypeService service) {
        this.service = service;
    }

    @Override
    protected TeachingTypeService getService() {
        return service;
    }
}
