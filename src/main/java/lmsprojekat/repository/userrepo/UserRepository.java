package lmsprojekat.repository.userrepo;

import java.util.Optional;

import lmsprojekat.model.users.User;
import lmsprojekat.repository.SoftDeleteRepository;

public interface UserRepository extends SoftDeleteRepository<User, Long> {
    Optional<User> findByEmail(String name);

}