package lmsprojekat.repository.studentrepo;

import java.util.List;

import lmsprojekat.model.student.StudyProgram;
import lmsprojekat.repository.SoftDeleteRepository;

public interface StudyProgramRepository extends SoftDeleteRepository<StudyProgram, Long> {
	
	List<StudyProgram> findByFacultyId(Long facultyId);

}
