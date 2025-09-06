package lmsprojekat.controller.studentcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.studentdto.StudyYearDTO;
import lmsprojekat.service.studentservice.StudyYearService;

@RestController
@RequestMapping("/api/studyYear")
public class StudyYearController extends BaseCrudController<StudyYearDTO, Long> {

    private final StudyYearService service;

    public StudyYearController(StudyYearService service) {
        this.service = service;
    }

    @Override
    protected StudyYearService getService() {
        return service;
    }
}
