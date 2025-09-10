package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface TeachingMaterialRepository extends SoftDeleteRepository<TeachingMaterial, Long> {

  @Query("""
    select distinct tm
    from TeachingMaterial tm
    left join fetch tm.files f
    where tm.learningOutcome.id = :loId
      and coalesce(tm.deleted,false) = false
  """)
  List<TeachingMaterial> findAllWithFilesByLearningOutcomeId(@Param("loId") Long learningOutcomeId);
}
