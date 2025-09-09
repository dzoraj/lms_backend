package lmsprojekat.repository.userrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.users.Student;
import lmsprojekat.repository.SoftDeleteRepository;
@Repository
public interface StudentRepository extends SoftDeleteRepository<Student, Long> {

	@Query("""
			  SELECT DISTINCT s
			  FROM TeacherOnCourse toc
			    JOIN toc.courseRealization cr
			    JOIN cr.subject subj
			    JOIN StudentInYear siy ON siy.studyYear = subj.studyYear
			    JOIN siy.student s
			  WHERE toc.teacher.id = :teacherId
			    AND toc.deleted = false
			    AND cr.deleted = false
			    AND (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')))
			    AND (:indexNumber IS NULL OR siy.indexNumber LIKE CONCAT('%', :indexNumber, '%'))
			    AND (:enrollmentYear IS NULL OR YEAR(siy.enrollmentDate) = :enrollmentYear)
			  """)
			List<Student> teacherScopedSearch(
			    @Param("teacherId") Long teacherId,
			    @Param("name") String name,
			    @Param("indexNumber") String indexNumber,
			    @Param("enrollmentYear") Integer enrollmentYear
			);
	

    @Query("""
      SELECT AVG(ca.konacnaOcena)
      FROM CourseAttendance ca
      WHERE ca.student.id = :studentId AND ca.konacnaOcena IS NOT NULL
    """)
    Double avgGrade(@Param("studentId") Long studentId);

    @Query("""
      SELECT COALESCE(SUM(subj.espb),0)
      FROM CourseAttendance ca
      JOIN CourseRealization cr ON cr.id = ca.courseRealization.id
      JOIN Subject subj ON subj.id = cr.subject.id
      WHERE ca.student.id = :studentId AND ca.konacnaOcena IS NOT NULL
    """)
    Integer ectsForPassed(@Param("studentId") Long studentId);

    @Query("""
      SELECT siy.indexNumber FROM StudentInYear siy
      WHERE siy.student.id = :studentId
      ORDER BY siy.enrollmentDate DESC
    """)
    List<String> latestIndex(@Param("studentId") Long studentId);

    @Query("""
      SELECT EXTRACT(YEAR FROM siy.enrollmentDate)
      FROM StudentInYear siy
      WHERE siy.student.id = :studentId
      ORDER BY siy.enrollmentDate DESC
    """)
    List<Integer> latestEnrollmentYear(@Param("studentId") Long studentId);
}
