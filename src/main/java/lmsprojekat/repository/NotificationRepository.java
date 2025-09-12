package lmsprojekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.Notification;

@Repository
public interface NotificationRepository extends SoftDeleteRepository<Notification, Long> {

    List<Notification> findByCourseRealizationId(Long courseId);

    @Query("""
        SELECT n 
        FROM Notification n
        JOIN CourseRealization cr ON n.courseRealization.id = cr.id
        JOIN CourseAttendance ca ON ca.courseRealization.id = cr.id
        WHERE ca.student.id = :studentId
    """)
    List<Notification> findByStudentId(@Param("studentId") Long studentId);

    @Query("""
        SELECT n 
        FROM Notification n
        WHERE n.teacherOnCourse.teacher.id = :teacherId
    """)
    List<Notification> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("""
        SELECT n
        FROM Notification n
        WHERE n.teacherOnCourse.teacher.id = :teacherId
          AND n.courseRealization.subject.id = :subjectId
    """)
    List<Notification> findByTeacherIdAndSubjectId(@Param("teacherId") Long teacherId,
                                                   @Param("subjectId") Long subjectId);
}
