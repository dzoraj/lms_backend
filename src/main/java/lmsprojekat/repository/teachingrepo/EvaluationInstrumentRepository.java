package lmsprojekat.repository.teachingrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface EvaluationInstrumentRepository extends SoftDeleteRepository<EvaluationInstrument, Long> {}
