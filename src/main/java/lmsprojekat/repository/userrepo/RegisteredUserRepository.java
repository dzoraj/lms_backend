package lmsprojekat.repository.userrepo;

import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.repository.SoftDeleteRepository;

public interface RegisteredUserRepository extends SoftDeleteRepository<RegisteredUser, Long> {
}

