package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.EducationalGoalDTO;
import lmsprojekat.service.teachingservice.EducationalGoalService;

@RestController
@RequestMapping("/api/educationalGoal")
public class EducationalGoalController extends BaseCrudController<EducationalGoalDTO, Long> {

    private final EducationalGoalService service;

    public EducationalGoalController(EducationalGoalService service) {
        this.service = service;
    }

    @Override
    protected EducationalGoalService getService() {
        return service;
    }
}
