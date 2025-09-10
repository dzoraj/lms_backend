package lmsprojekat.repository.userrepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lmsprojekat.model.users.User;
import lmsprojekat.repository.SoftDeleteRepository;

public interface UserRepository extends SoftDeleteRepository<User, Long> {
    Optional<User> findByEmail(String name);

    @Query("""
    	       select coalesce(u.name, u.email)
    	       from User u
    	       where u.id = :id and coalesce(u.deleted,false) = false
    	    """)
    	    String getDisplayName(@Param("id") Long id);
    	}