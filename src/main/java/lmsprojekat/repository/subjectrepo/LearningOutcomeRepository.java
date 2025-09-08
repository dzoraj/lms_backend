package lmsprojekat.repository.subjectrepo;

import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LearningOutcomeRepository extends SoftDeleteRepository<LearningOutcome, Long> {}
