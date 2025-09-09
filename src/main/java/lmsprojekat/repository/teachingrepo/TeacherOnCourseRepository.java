package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.repository.SoftDeleteRepository;
@Repository
public interface TeacherOnCourseRepository extends SoftDeleteRepository<TeacherOnCourse, Long> {

    @Query("SELECT DISTINCT s FROM TeacherOnCourse toc " +
           "JOIN toc.courseRealization cr " +
           "JOIN cr.subject s " +
           "WHERE toc.teacher.id = :teacherId")
    List<Subject> findSubjectsByTeacherId(@Param("teacherId") Long teacherId);
}
