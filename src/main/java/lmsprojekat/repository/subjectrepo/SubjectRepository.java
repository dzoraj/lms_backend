package lmsprojekat.repository.subjectrepo;

import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.SoftDeleteRepository;

public interface SubjectRepository extends SoftDeleteRepository<Subject, Long> {}
