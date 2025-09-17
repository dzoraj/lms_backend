package lmsprojekat.controller.quizcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.quizdto.QuizDefinitionDTO;
import lmsprojekat.dto.quizdto.QuizSubmissionDTO;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.service.quizservice.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) { this.service = service; }

    @GetMapping("/definition/{knowledgeEvaluationId}")
    public ResponseEntity<QuizDefinitionDTO> get(@PathVariable Long knowledgeEvaluationId) {
        return ResponseEntity.ok(service.getByEvaluation(knowledgeEvaluationId));
    }

    @PutMapping("/definition/{knowledgeEvaluationId}")
    public ResponseEntity<QuizDefinitionDTO> put(@PathVariable Long knowledgeEvaluationId, @RequestBody QuizDefinitionDTO dto) {
        return ResponseEntity.ok(service.putDefinition(knowledgeEvaluationId, dto));
    }

    @PostMapping("/submit")
    public ResponseEntity<EvaluationAttemptDTO> submit(@RequestBody QuizSubmissionDTO submission) {
        return ResponseEntity.ok(service.submitAndAutoPublish(submission));
    }
}
