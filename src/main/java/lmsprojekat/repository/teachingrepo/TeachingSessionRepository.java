package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeachingSessionRepository extends SoftDeleteRepository<TeachingSession, Long> {}
