package lmsprojekat.repository.subjectrepo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.SoftDeleteRepository;

@Repository
public interface SubjectRepository extends SoftDeleteRepository<Subject, Long> {

  @Query("""
    select s
    from Subject s
    join s.studyYear sy
    join sy.studyProgram sp
    where sp.id = :programId
      and coalesce(s.deleted,false) = false
    order by s.name asc
  """)
  List<Subject> findAllByStudyProgramId(@Param("programId") Long programId);

  @Query("""
    select distinct s
    from Subject s
    left join fetch s.syllabus lo
    where s.id = :id and coalesce(s.deleted,false) = false
  """)
  Subject fetchWithSyllabus(@Param("id") Long id);
}
