package lmsprojekat.repository.gradingrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.grading.GradeBoundary;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface GradeBoundaryRepository extends SoftDeleteRepository<GradeBoundary, Long> {}