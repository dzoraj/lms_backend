package lmsprojekat.controller.subjectcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.service.subjectservice.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends BaseCrudController<SubjectDTO, Long> {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @Override
    protected SubjectService getService() {
        return service;
    }
}
