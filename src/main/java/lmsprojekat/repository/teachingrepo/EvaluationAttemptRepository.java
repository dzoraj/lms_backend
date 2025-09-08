package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.EvaluationAttempt;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface EvaluationAttemptRepository extends SoftDeleteRepository<EvaluationAttempt, Long> {   
	
	@Query("""
		    SELECT ea.points
		    FROM EvaluationAttempt ea
		    JOIN ea.evaluation ke
		    JOIN ke.courseRealization cr
		    JOIN cr.subject s
		    WHERE ea.deleted = false
		      AND ke.deleted = false
		      AND cr.deleted = false
		      AND s.deleted = false
		      AND ea.studentInYear.student.id = :studentId
		      AND s.id = :subjectId
		    ORDER BY ea.id DESC
		""")
		List<Integer> findPointsByStudentAndSubject(
		    @Param("studentId") Long studentId,
		    @Param("subjectId") Long subjectId
		);

    }