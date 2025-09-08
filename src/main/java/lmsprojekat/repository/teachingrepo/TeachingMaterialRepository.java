package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeachingMaterialRepository extends SoftDeleteRepository<TeachingMaterial, Long> {}
