package lmsprojekat.repository.subjectrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lmsprojekat.model.subject.CourseAttendance;
import lmsprojekat.repository.SoftDeleteRepository;

public interface CourseAttendanceRepository extends SoftDeleteRepository<CourseAttendance, Long> {

    @Query("SELECT ca FROM CourseAttendance ca " +
           "JOIN FETCH ca.courseRealization cr " +
           "JOIN FETCH cr.subject s " +
           "WHERE ca.student.id = :studentId AND ca.deleted = false")
    List<CourseAttendance> findAllByStudentIdWithRealizationAndSubject(@Param("studentId") Long studentId);
}
