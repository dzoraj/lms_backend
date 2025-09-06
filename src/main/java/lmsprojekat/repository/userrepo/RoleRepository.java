package lmsprojekat.repository.userrepo;

import java.util.Optional;

import lmsprojekat.model.users.Role;
import lmsprojekat.repository.SoftDeleteRepository;

public interface RoleRepository extends SoftDeleteRepository<Role, Long> {
    Optional<Role> findByName(String name);

	boolean existsByName(String name);
}