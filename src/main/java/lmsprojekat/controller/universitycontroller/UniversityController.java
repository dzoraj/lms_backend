package lmsprojekat.controller.universitycontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.universitydto.UniversityDTO;
import lmsprojekat.service.universityservice.UniversityService;

@RestController
@RequestMapping("/api/university")
public class UniversityController extends BaseCrudController<UniversityDTO, Long> {

    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @Override
    protected UniversityService getService() {
        return service;
    }
}
