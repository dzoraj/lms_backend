package lmsprojekat.repository.subjectrepo;

import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRealizationRepository extends SoftDeleteRepository<CourseRealization, Long> {}
