package lmsprojekat.repository.studentrepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.student.StudyProgram;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface StudyProgramRepository extends SoftDeleteRepository<StudyProgram, Long> {

 List<StudyProgram> findByFacultyId(Long facultyId);

 @Query("""
   select sp
   from StudyProgram sp
   left join fetch sp.leader t
   where sp.id = :id and coalesce(sp.deleted,false) = false
 """)
 Optional<StudyProgram> fetchWithLeader(@Param("id") Long id);
}
