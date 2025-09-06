package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;
import lmsprojekat.service.teachingservice.TeachingMaterialService;

@RestController
@RequestMapping("/api/teachingMaterial")
public class TeachingMaterialController extends BaseCrudController<TeachingMaterialDTO, Long> {

    private final TeachingMaterialService service;

    public TeachingMaterialController(TeachingMaterialService service) {
        this.service = service;
    }

    @Override
    protected TeachingMaterialService getService() {
        return service;
    }
}
