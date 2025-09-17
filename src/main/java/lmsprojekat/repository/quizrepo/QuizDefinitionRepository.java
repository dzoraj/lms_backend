package lmsprojekat.repository.quizrepo;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.quiz.QuizDefinition;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface QuizDefinitionRepository extends SoftDeleteRepository<QuizDefinition, Long> {
    Optional<QuizDefinition> findByKnowledgeEvaluation_IdAndDeletedFalse(Long knowledgeEvaluationId);
}
