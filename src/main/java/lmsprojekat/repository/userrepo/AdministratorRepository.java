package lmsprojekat.repository.userrepo;

import lmsprojekat.model.users.Administrator;
import lmsprojekat.repository.SoftDeleteRepository;

public interface AdministratorRepository extends SoftDeleteRepository<Administrator, Long> {
    Administrator findByEmail(String email);
}
