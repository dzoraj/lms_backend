package lmsprojekat.repository.userrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.users.Student;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface StudentRepository extends SoftDeleteRepository<Student, Long> {
}
