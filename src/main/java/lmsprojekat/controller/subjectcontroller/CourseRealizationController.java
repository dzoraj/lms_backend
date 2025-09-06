package lmsprojekat.controller.subjectcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.service.subjectservice.CourseRealizationService;

@RestController
@RequestMapping("/api/courseRealization")
public class CourseRealizationController extends BaseCrudController<CourseRealizationDTO, Long> {

    private final CourseRealizationService service;

    public CourseRealizationController(CourseRealizationService service) {
        this.service = service;
    }

    @Override
    protected CourseRealizationService getService() {
        return service;
    }
}
