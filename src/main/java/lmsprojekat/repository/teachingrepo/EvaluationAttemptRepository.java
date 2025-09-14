package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
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
    
    @Query("""
        SELECT ea
        FROM EvaluationAttempt ea
        JOIN ea.studentInYear siy
        JOIN siy.student s
        WHERE s.id = :studentId
    """)
    List<EvaluationAttempt> findAllByStudentId(@Param("studentId") Long studentId);

    boolean existsByEvaluation_IdAndStudentInYear_Id(Long evaluationId, Long studentInYearId);
    
    @Query("""
        SELECT ea
        FROM EvaluationAttempt ea
        JOIN FETCH ea.evaluation ke
        JOIN ke.courseRealization cr
        JOIN cr.subject s
        WHERE ea.deleted = false
          AND ke.deleted = false
          AND cr.deleted = false
          AND s.deleted = false
          AND ea.studentInYear.student.id = :studentId
          AND s.id = :subjectId
    """)
    List<EvaluationAttempt> findAllByStudentAndSubject(
        @Param("studentId") Long studentId,
        @Param("subjectId") Long subjectId
    );

    @Query("""
        SELECT ea
        FROM EvaluationAttempt ea
        JOIN ea.evaluation ke
        JOIN ke.courseRealization cr
        JOIN cr.subject s
        WHERE ea.deleted = false
          AND ke.deleted = false
          AND cr.deleted = false
          AND s.deleted = false
          AND ea.isLatest = true
          AND ea.studentInYear.student.id = :studentId
          AND s.id = :subjectId
    """)
    List<EvaluationAttempt> findLatestByStudentAndSubject(
        @Param("studentId") Long studentId,
        @Param("subjectId") Long subjectId
    );

    @Query("""
        SELECT ea
        FROM EvaluationAttempt ea
        WHERE ea.evaluation.id = :evaluationId
          AND ea.studentInYear.id = :studentInYearId
          AND ea.deleted = false
    """)
    List<EvaluationAttempt> findByEvaluation_IdAndStudentInYear_Id(
        @Param("evaluationId") Long evaluationId,
        @Param("studentInYearId") Long studentInYearId
    );

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        UPDATE EvaluationAttempt ea
        SET ea.isLatest = false
        WHERE ea.studentInYear.id = :studentInYearId
          AND ea.evaluation.courseRealization.id = :courseRealizationId
          AND ea.evaluation.evaluationType.id = :evaluationTypeId
          AND ea.deleted = false
    """)
    void markOldAttemptsAsNotLatest(
        @Param("studentInYearId") Long studentInYearId,
        @Param("courseRealizationId") Long courseRealizationId,
        @Param("evaluationTypeId") Long evaluationTypeId
    );


}
