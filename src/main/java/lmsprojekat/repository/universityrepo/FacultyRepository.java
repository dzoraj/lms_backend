package lmsprojekat.repository.universityrepo;

import lmsprojekat.model.university.Faculty;
import lmsprojekat.repository.SoftDeleteRepository;

public interface FacultyRepository extends SoftDeleteRepository<Faculty, Long> {}
