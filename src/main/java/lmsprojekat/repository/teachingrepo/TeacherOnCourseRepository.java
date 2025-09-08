package lmsprojekat.repository.teachingrepo;

import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeacherOnCourseRepository extends SoftDeleteRepository<TeacherOnCourse, Long> {}
