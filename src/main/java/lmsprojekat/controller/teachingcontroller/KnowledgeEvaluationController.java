package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.service.teachingservice.KnowledgeEvaluationService;

@RestController
@RequestMapping("/api/knowledgeEvaluation")
public class KnowledgeEvaluationController extends BaseCrudController<KnowledgeEvaluationDTO, Long> {

    private final KnowledgeEvaluationService service;

    public KnowledgeEvaluationController(KnowledgeEvaluationService service) {
        this.service = service;
    }

    @Override
    protected KnowledgeEvaluationService getService() {
        return service;
    }
}
