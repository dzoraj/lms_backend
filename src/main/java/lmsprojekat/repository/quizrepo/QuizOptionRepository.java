package lmsprojekat.repository.quizrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.quiz.QuizOption;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface QuizOptionRepository extends SoftDeleteRepository<QuizOption, Long> {

    @Query("SELECT o FROM QuizOption o WHERE o.deleted=false AND o.question.id=:questionId ORDER BY o.orderIndex ASC, o.id ASC")
    List<QuizOption> findByQuestionIdOrdered(@Param("questionId") Long questionId);

    @Query("SELECT o FROM QuizOption o WHERE o.deleted=false AND o.question.id IN :questionIds")
    List<QuizOption> findByQuestionIds(@Param("questionIds") List<Long> questionIds);
}
