package lmsprojekat.repository.subjectrepo;

import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubjectRepository extends SoftDeleteRepository<Subject, Long> {}
