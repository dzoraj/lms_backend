package lmsprojekat.repository.universityrepo;

import lmsprojekat.model.university.University;
import lmsprojekat.repository.SoftDeleteRepository;

public interface UniversityRepository extends SoftDeleteRepository<University, Long> {}
