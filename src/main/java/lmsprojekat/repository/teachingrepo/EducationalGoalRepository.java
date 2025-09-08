package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.EducationalGoal;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EducationalGoalRepository extends SoftDeleteRepository<EducationalGoal, Long> {}
