package lmsprojekat.repository.userrepo;

import org.springframework.stereotype.Repository;

import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface TeacherRepository extends SoftDeleteRepository<Teacher, Long> {
}