package lmsprojekat.service.studentservice;

import java.util.List;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudyProgramDTO;
import lmsprojekat.model.student.StudyProgram;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.studentrepo.StudyProgramRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class StudyProgramService extends AbstractCrudService<StudyProgramDTO, StudyProgram, Long> {

    private final StudyProgramRepository studyProgramRepository;
    private final TeacherRepository teacherRepository;
    private final     FacultyRepository facultyRepository;

    public StudyProgramService(StudyProgramRepository studyProgramRepository, TeacherRepository teacherRepository,FacultyRepository facultyRepository) {
        this.studyProgramRepository = studyProgramRepository;
        this.teacherRepository = teacherRepository;
        this.facultyRepository = facultyRepository;
        
    }

    @Override
    protected StudyProgramRepository getRepository() {
        return studyProgramRepository;
    }

    @Override
    protected StudyProgramDTO toDTO(StudyProgram entity) {
        return new StudyProgramDTO(
            entity.getId(),
            entity.getName(),
            entity.getLeader() != null ? entity.getLeader().getId() : null,
            entity.getFaculty() != null ? entity.getFaculty().getId() : null
        );
    }

    @Override
    protected StudyProgram toEntity(StudyProgramDTO dto) {
        StudyProgram entity = new StudyProgram();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getLeaderId() != null) {
            Teacher leader = teacherRepository.findById(dto.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher (leader) not found with id " + dto.getLeaderId()));
            entity.setLeader(leader);
        }
        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"));
            entity.setFaculty(faculty);
        }


        return entity;
    }

    @Override
    protected void updateEntity(StudyProgram entity, StudyProgramDTO dto) {
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getLeaderId() != null) {
            Teacher leader = teacherRepository.findById(dto.getLeaderId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher (leader) not found with id " + dto.getLeaderId()));
            entity.setLeader(leader);
        }

        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found"));
            entity.setFaculty(faculty);
        }
    }
    public List<StudyProgramDTO> getByFacultyId(Long facultyId) {
        List<StudyProgram> programs = studyProgramRepository.findByFacultyId(facultyId);
        return programs.stream().map(this::toDTO).toList();
    }

}
