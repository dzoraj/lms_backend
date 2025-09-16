package lmsprojekat.repository.teachingrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.teaching.MaterialLoan;
import lmsprojekat.repository.SoftDeleteRepository;
@Repository
public interface MaterialLoanRepository extends SoftDeleteRepository<MaterialLoan, Long> {

}
