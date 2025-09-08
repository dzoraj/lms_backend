package lmsprojekat.repository.userrepo;

import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RegisteredUserRepository extends SoftDeleteRepository<RegisteredUser, Long> {
}

