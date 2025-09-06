package lmsprojekat.controller.titlecontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.titledto.TitleDTO;
import lmsprojekat.service.titleservice.TitleService;

@RestController
@RequestMapping("/api/title")
public class TitleController extends BaseCrudController<TitleDTO, Long> {

    private final TitleService service;

    public TitleController(TitleService service) {
        this.service = service;
    }

    @Override
    protected TitleService getService() {
        return service;
    }
}
