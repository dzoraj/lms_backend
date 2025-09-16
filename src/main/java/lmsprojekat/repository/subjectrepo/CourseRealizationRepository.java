package lmsprojekat.repository.subjectrepo;

import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRealizationRepository extends SoftDeleteRepository<CourseRealization, Long> {

    @Query("""
        SELECT cr
        FROM CourseRealization cr
        WHERE cr.subject.id = :subjectId
          AND cr.deleted = false
        ORDER BY cr.id ASC
    """)
    Optional<CourseRealization> findFirstBySubjectId(@Param("subjectId") Long subjectId);


}
