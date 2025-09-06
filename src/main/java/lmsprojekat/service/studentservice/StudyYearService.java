package lmsprojekat.service.studentservice;

import org.springframework.stereotype.Service;
import lmsprojekat.dto.studentdto.StudyYearDTO;
import lmsprojekat.model.student.StudyProgram;
import lmsprojekat.model.student.StudyYear;
import lmsprojekat.repository.studentrepo.StudyYearRepository;
import lmsprojekat.repository.studentrepo.StudyProgramRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class StudyYearService extends AbstractCrudService<StudyYearDTO, StudyYear, Long> {

    private final StudyYearRepository studyYearRepository;
    private final StudyProgramRepository studyProgramRepository;

    public StudyYearService(StudyYearRepository studyYearRepository, StudyProgramRepository studyProgramRepository) {
        this.studyYearRepository = studyYearRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    protected StudyYearRepository getRepository() {
        return studyYearRepository;
    }

    @Override
    protected StudyYearDTO toDTO(StudyYear entity) {
        return new StudyYearDTO(
            entity.getId(),
            entity.getEnrollmentDate(),
            entity.getStudyProgram() != null ? entity.getStudyProgram().getId() : null
        );
    }

    @Override
    protected StudyYear toEntity(StudyYearDTO dto) {
        StudyYear entity = new StudyYear();
        entity.setId(dto.getId());
        entity.setEnrollmentDate(dto.getEnrollmentDate());

        if (dto.getStudyProgramId() != null) {
            StudyProgram sp = studyProgramRepository.findById(dto.getStudyProgramId())
                .orElseThrow(() -> new IllegalArgumentException("StudyProgram not found with id " + dto.getStudyProgramId()));
            entity.setStudyProgram(sp);
        }

        return entity;
    }

    @Override
    protected void updateEntity(StudyYear entity, StudyYearDTO dto) {
        if (dto.getEnrollmentDate() != null) {
            entity.setEnrollmentDate(dto.getEnrollmentDate());
        }

        if (dto.getStudyProgramId() != null) {
            StudyProgram sp = studyProgramRepository.findById(dto.getStudyProgramId())
                .orElseThrow(() -> new IllegalArgumentException("StudyProgram not found with id " + dto.getStudyProgramId()));
            entity.setStudyProgram(sp);
        }
    }
}
