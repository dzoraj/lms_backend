package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.TeachingType;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeachingTypeRepository extends SoftDeleteRepository<TeachingType, Long> {}
