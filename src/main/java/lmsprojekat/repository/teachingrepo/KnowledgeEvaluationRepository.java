package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface KnowledgeEvaluationRepository extends SoftDeleteRepository<KnowledgeEvaluation, Long> {}
