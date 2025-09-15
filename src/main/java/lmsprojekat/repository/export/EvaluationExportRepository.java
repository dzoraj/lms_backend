package lmsprojekat.repository.export;

import java.util.List;

import lmsprojekat.model.teaching.KnowledgeEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationExportRepository
        extends org.springframework.data.repository.Repository<KnowledgeEvaluation, Long> {

    @Query(value = """
        SELECT 
          ke.id                                                AS id,
          DATE_FORMAT(ke.start_time, '%Y-%m-%d %H:%i')        AS startTime,
          DATE_FORMAT(ke.end_time,   '%Y-%m-%d %H:%i')        AS endTime,
          ke.points                                           AS maxPoints,
          et.name                                             AS evaluationType,
          ei.name                                             AS instrument,
          cr.id                                               AS courseRealizationId,
          s.name                                              AS subjectName
        FROM knowledge_evaluation ke
          LEFT JOIN evaluation_type et        ON et.id = ke.evaluation_type_id
          LEFT JOIN evaluation_instrument ei  ON ei.id = ke.evaluation_instrument_id
          JOIN course_realization cr          ON cr.id = ke.course_realization_id
          LEFT JOIN subject s                 ON s.id = cr.subject_id
        WHERE ke.id = :id
        """, nativeQuery = true)
    EvalHeader findEvaluationHeader(@Param("id") Long id);

    @Query(value = """
        SELECT 
          ea.id                    AS attemptId,
          ea.points                AS points,
          ea.is_latest             AS latest,
          ea.note                  AS note,
          siy.id                   AS studentInYearId,
          siy.index_number         AS indexNumber,
          st.id                    AS studentId,
          u.name                   AS studentName,
          u.email                  AS studentEmail
        FROM evaluation_attempt ea
          JOIN student_in_year siy  ON siy.id = ea.student_in_year_id
          JOIN student st           ON st.id  = siy.student_id
          JOIN registered_user ru   ON ru.id  = st.id
          JOIN users u              ON u.id   = ru.id
        WHERE ea.evaluation_id = :id
          AND (ea.deleted = 0 OR ea.deleted IS NULL)
          AND (u.deleted  = 0 OR u.deleted  IS NULL)
        ORDER BY u.name ASC, ea.id ASC
        """, nativeQuery = true)
    List<EvalAttemptRow> findAttempts(@Param("id") Long id);
}
