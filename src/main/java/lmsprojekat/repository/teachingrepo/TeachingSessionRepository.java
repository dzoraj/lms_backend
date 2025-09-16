package lmsprojekat.repository.teachingrepo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface TeachingSessionRepository extends SoftDeleteRepository<TeachingSession, Long> {

    List<TeachingSession> findByCourseRealization_Subject_StudyYear_IdAndStartTimeBetween(
            Long studyYearId, LocalDateTime from, LocalDateTime to);

    @Query("""
      SELECT ts FROM TeachingSession ts
      WHERE ts.deleted = false
        AND (:courseRealizationId IS NULL OR ts.courseRealization.id = :courseRealizationId)
        AND ts.startTime < :end AND ts.endTime > :start
    """)
    List<TeachingSession> findOverlaps(
            @Param("courseRealizationId") Long courseRealizationId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    @Query("""
      SELECT ts FROM TeachingSession ts
      JOIN ts.courseRealization cr
      JOIN TeacherOnCourse toc ON toc.courseRealization.id = cr.id
      WHERE ts.deleted = false
        AND toc.teacher.id = :teacherId
        AND ts.startTime < :end AND ts.endTime > :start
    """)
    List<TeachingSession> findTeacherOverlaps(
            @Param("teacherId") Long teacherId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
