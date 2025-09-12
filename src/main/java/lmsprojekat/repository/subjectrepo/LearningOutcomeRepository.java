package lmsprojekat.repository.subjectrepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.repository.SoftDeleteRepository;
@Repository
public interface LearningOutcomeRepository extends SoftDeleteRepository<LearningOutcome, Long> {
    List<LearningOutcome> findBySubject_Id(Long subjectId);
}
