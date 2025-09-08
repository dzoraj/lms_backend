package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.EvaluationType;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EvaluationTypeRepository extends SoftDeleteRepository<EvaluationType, Long> {}
