package lmsprojekat.controller.universitycontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.universitydto.FacultyDTO;
import lmsprojekat.service.universityservice.FacultyService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController extends BaseCrudController<FacultyDTO, Long> {

    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }
    
    @Override
    protected FacultyService getService() {
        return service;
    }
}
