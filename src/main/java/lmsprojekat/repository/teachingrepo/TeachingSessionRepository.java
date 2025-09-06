package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.repository.SoftDeleteRepository;

public interface TeachingSessionRepository extends SoftDeleteRepository<TeachingSession, Long> {}
