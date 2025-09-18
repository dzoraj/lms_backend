package lmsprojekat.repository.teachingrepo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface KnowledgeEvaluationRepository extends SoftDeleteRepository<KnowledgeEvaluation, Long> {

    List<KnowledgeEvaluation> findByCourseRealization_Subject_StudyYear_Id(Long studyYearId);

    @Query("""
      SELECT ke FROM KnowledgeEvaluation ke
      WHERE ke.deleted = false
        AND (:courseRealizationId IS NULL OR ke.courseRealization.id = :courseRealizationId)
        AND ke.startTime < :end AND ke.endTime > :start
    """)
    List<KnowledgeEvaluation> findOverlaps(
            @Param("courseRealizationId") Long courseRealizationId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("""
      SELECT ke FROM KnowledgeEvaluation ke
      JOIN ke.courseRealization cr
      JOIN TeacherOnCourse toc ON toc.courseRealization.id = cr.id
      WHERE ke.deleted = false
        AND toc.teacher.id = :teacherId
        AND ke.startTime < :end AND ke.endTime > :start
    """)
    List<KnowledgeEvaluation> findTeacherOverlaps(
            @Param("teacherId") Long teacherId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("""
      SELECT ke FROM KnowledgeEvaluation ke
      WHERE ke.deleted = false
        AND ke.courseRealization.subject.studyYear.id = :studyYearId
        AND ke.startTime >= :from AND ke.endTime <= :to
    """)
    List<KnowledgeEvaluation> findByStudyYearAndRange(
            @Param("studyYearId") Long studyYearId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to);
    
    
    
    
    
    @Query("""
    	      SELECT ke FROM KnowledgeEvaluation ke
    	      WHERE ke.deleted = false
    	        AND ke.courseRealization.subject.id = :subjectId
    	      ORDER BY ke.startTime ASC, ke.id ASC
    	    """)
    	    List<KnowledgeEvaluation> findBySubjectId(@Param("subjectId") Long subjectId);
}
