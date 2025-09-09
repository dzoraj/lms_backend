package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface EvaluationInstrumentRepository extends SoftDeleteRepository<EvaluationInstrument, Long> {

    @Query("SELECT ei FROM EvaluationInstrument ei " +
           "JOIN KnowledgeEvaluation ke ON ke.evaluationInstrument.id = ei.id " +
           "JOIN CourseRealization cr ON ke.courseRealization.id = cr.id " +
           "JOIN TeacherOnCourse toc ON toc.courseRealization.id = cr.id " +
           "WHERE toc.teacher.id = :teacherId")
    List<EvaluationInstrument> findByTeacherId(@Param("teacherId") Long teacherId);
}
