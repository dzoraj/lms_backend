package lmsprojekat.controller.subjectcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.service.subjectservice.LearningOutcomeService;

@RestController
@RequestMapping("/api/learningOutcome")
public class LearningOutcomeController extends BaseCrudController<LearningOutcomeDTO, Long> {

    private final LearningOutcomeService service;

    public LearningOutcomeController(LearningOutcomeService service) {
        this.service = service;
    }

    @Override
    protected LearningOutcomeService getService() {
        return service;
    }
}
