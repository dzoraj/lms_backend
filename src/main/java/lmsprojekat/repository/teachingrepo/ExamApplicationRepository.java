package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.ExamApplication;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface ExamApplicationRepository extends SoftDeleteRepository<ExamApplication, Long> {

    List<ExamApplication> findByStudentInYear(StudentInYear studentInYear);

    boolean existsByStudentInYearAndKnowledgeEvaluation_Id(StudentInYear studentInYear, Long knowledgeEvaluationId);
}
