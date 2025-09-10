package lmsprojekat.repository.studentrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.repository.SoftDeleteRepository;

public interface StudentInYearRepository extends SoftDeleteRepository<StudentInYear, Long> {
	@Query("""
			  SELECT siy
			  FROM StudentInYear siy
			  WHERE siy.student.id = :studentId
			  ORDER BY siy.enrollmentDate DESC
			""")
			List<StudentInYear> findAllByStudentId(@Param("studentId") Long studentId);
}
