package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.ExamApplication;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface ExamApplicationRepository extends SoftDeleteRepository<ExamApplication, Long> {

    List<ExamApplication> findByStudentInYear(StudentInYear studentInYear);

    boolean existsByStudentInYearAndKnowledgeEvaluation_Id(StudentInYear studentInYear, Long knowledgeEvaluationId);

    @Query("""
        SELECT ea
        FROM ExamApplication ea
        JOIN ea.knowledgeEvaluation ke
        JOIN ke.courseRealization cr
        JOIN TeacherOnCourse toc ON toc.courseRealization.id = cr.id
        WHERE ea.id = :applicationId
          AND toc.teacher.id = :teacherId
    """)
    ExamApplication findAuthorizedApplication(Long applicationId, Long teacherId);
    @Query("""
    	    SELECT ea
    	    FROM ExamApplication ea
    	    JOIN ea.knowledgeEvaluation ke
    	    JOIN ke.courseRealization cr
    	    JOIN TeacherOnCourse toc ON toc.courseRealization.id = cr.id
    	    WHERE toc.teacher.id = :teacherId
    	      AND cr.subject.id = :subjectId
    	      AND ea.deleted = false
    	""")
    	List<ExamApplication> findByTeacherAndSubject(Long teacherId, Long subjectId);
}
