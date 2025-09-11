package lmsprojekat.repository.teachingrepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface KnowledgeEvaluationRepository extends SoftDeleteRepository<KnowledgeEvaluation, Long> {

    List<KnowledgeEvaluation> findByCourseRealization_Subject_StudyYear_Id(Long studyYearId);
}
