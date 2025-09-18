package lmsprojekat.controller.teachingcontroller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.service.teachingservice.KnowledgeEvaluationService;

@RestController
@RequestMapping("/api/knowledgeEvaluation")
public class KnowledgeEvaluationController extends BaseCrudController<KnowledgeEvaluationDTO, Long> {

    private final KnowledgeEvaluationService service;

    public KnowledgeEvaluationController(KnowledgeEvaluationService service) { this.service = service; }

    @Override
    protected KnowledgeEvaluationService getService() { return service; }

    @GetMapping("/by-subject/{subjectId}")
    public List<KnowledgeEvaluationDTO> bySubject(@PathVariable Long subjectId) {
        return service.findBySubject(subjectId);
    }
}
