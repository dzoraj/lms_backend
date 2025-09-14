package lmsprojekat.repository.studentrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.student.StudyYear;
import lmsprojekat.repository.SoftDeleteRepository;
@Repository
public interface StudyYearRepository extends SoftDeleteRepository<StudyYear, Long> {}
