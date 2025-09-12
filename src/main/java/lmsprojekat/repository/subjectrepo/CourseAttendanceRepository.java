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
    @Query("""
    	    SELECT ca
    	    FROM CourseAttendance ca
    	    WHERE ca.student.id = :studentId
    	""")
    	List<CourseAttendance> findAllByStudentId(@Param("studentId") Long studentId);
    @Query("""
    	    SELECT ca
    	    FROM CourseAttendance ca
    	    JOIN ca.courseRealization cr
    	    JOIN cr.subject s
    	    WHERE ca.student.id = :studentId
    	      AND s.id = :subjectId
    	      AND ca.deleted = false
    	""")
    	CourseAttendance findByStudentAndSubject(@Param("studentId") Long studentId,
    	                                         @Param("subjectId") Long subjectId);

}
