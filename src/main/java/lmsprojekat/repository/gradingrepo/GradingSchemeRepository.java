package lmsprojekat.repository.gradingrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.grading.GradingScheme;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface GradingSchemeRepository extends SoftDeleteRepository<GradingScheme, Long> {}
