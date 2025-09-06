package lmsprojekat.controller.titlecontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.titledto.TitleTypeDTO;
import lmsprojekat.service.titleservice.TitleTypeService;

@RestController
@RequestMapping("/api/titleType")
public class TitleTypeController extends BaseCrudController<TitleTypeDTO, Long> {

    private final TitleTypeService service;

    public TitleTypeController(TitleTypeService service) {
        this.service = service;
    }

    @Override
    protected TitleTypeService getService() {
        return service;
    }
}
