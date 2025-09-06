package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.service.teachingservice.EvaluationAttemptService;

@RestController
@RequestMapping("/api/evaluationAttempt")
public class EvaluationAttemptController extends BaseCrudController<EvaluationAttemptDTO, Long> {

    private final EvaluationAttemptService service;

    public EvaluationAttemptController(EvaluationAttemptService service) {
        this.service = service;
    }

    @Override
    protected EvaluationAttemptService getService() {
        return service;
    }
}
