package lmsprojekat.repository.quizrepo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lmsprojekat.model.quiz.QuizQuestion;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface QuizQuestionRepository extends SoftDeleteRepository<QuizQuestion, Long> {

	@Query("SELECT q FROM QuizQuestion q WHERE q.deleted=false AND q.quiz.id=:quizId ORDER BY q.orderIndex ASC, q.id ASC")
	List<QuizQuestion> findByQuizIdOrdered(@Param("quizId") Long quizId);
}
