package lmsprojekat.service.subjectservice;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.studentdto.StudyYearDTO;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.model.student.StudyYear;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.studentrepo.StudyYearRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class SubjectService extends AbstractCrudService<SubjectDTO, Subject, Long> {

    private final SubjectRepository subjectRepository;
    private final StudyYearRepository studyYearRepository;

    public SubjectService(SubjectRepository subjectRepository, StudyYearRepository studyYearRepository) {
        this.subjectRepository = subjectRepository;
        this.studyYearRepository = studyYearRepository;
    }

    @Override
    protected SubjectRepository getRepository() {
        return subjectRepository;
    }

    @Override
    protected SubjectDTO toDTO(Subject entity) {
        if (entity == null) return null;

        StudyYearDTO studyYearDTO = null;
        if (entity.getStudyYear() != null) {
            studyYearDTO = new StudyYearDTO(
                entity.getStudyYear().getId(),
                entity.getStudyYear().getEnrollmentDate(),
                entity.getStudyYear().getStudyProgram() != null ? entity.getStudyYear().getStudyProgram().getId() : null
            );
        }

        return new SubjectDTO(
            entity.getId(),
            entity.getName(),
            entity.getEspb(),
            entity.getMandatory(),
            entity.getLectureCount(),
            entity.getLabCount(),
            entity.getOtherTeachingForms(),
            entity.getResearchWork(),
            entity.getOtherClasses(),
            studyYearDTO,
            null, 
            null, // add mapping later
            entity.getParentSubject() != null ? new SubjectDTO(entity.getParentSubject().getId(), null, null, null, null, null, null, null, null, null, null, null, null) : null
        );
    }

    @Override
    protected Subject toEntity(SubjectDTO dto) {
        if (dto == null) return null;

        Subject entity = new Subject();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEspb(dto.getEspb());
        entity.setMandatory(dto.getMandatory());
        entity.setLectureCount(dto.getLectureCount());
        entity.setLabCount(dto.getLabCount());
        entity.setOtherTeachingForms(dto.getOtherTeachingForms());
        entity.setResearchWork(dto.getResearchWork());
        entity.setOtherClasses(dto.getOtherClasses());

        if (dto.getStudyYear() != null && dto.getStudyYear().getId() != null) {
            StudyYear studyYear = studyYearRepository.findById(dto.getStudyYear().getId())
                .orElseThrow(() -> new IllegalArgumentException("StudyYear not found with id " + dto.getStudyYear().getId()));
            entity.setStudyYear(studyYear);
        }

        entity.setSyllabus(new ArrayList<>());
        entity.setSubSubjects(new ArrayList<>());

        if (dto.getParentSubject() != null && dto.getParentSubject().getId() != null) {
            Subject parent = subjectRepository.findById(dto.getParentSubject().getId())
                .orElseThrow(() -> new IllegalArgumentException("Parent Subject not found with id " + dto.getParentSubject().getId()));
            entity.setParentSubject(parent);
        }

        return entity;
    }

    @Override
    protected void updateEntity(Subject entity, SubjectDTO dto) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getEspb() != null) entity.setEspb(dto.getEspb());
        if (dto.getMandatory() != null) entity.setMandatory(dto.getMandatory());
        if (dto.getLectureCount() != null) entity.setLectureCount(dto.getLectureCount());
        if (dto.getLabCount() != null) entity.setLabCount(dto.getLabCount());
        if (dto.getOtherTeachingForms() != null) entity.setOtherTeachingForms(dto.getOtherTeachingForms());
        if (dto.getResearchWork() != null) entity.setResearchWork(dto.getResearchWork());
        if (dto.getOtherClasses() != null) entity.setOtherClasses(dto.getOtherClasses());

        if (dto.getStudyYear() != null && dto.getStudyYear().getId() != null) {
            StudyYear studyYear = studyYearRepository.findById(dto.getStudyYear().getId())
                .orElseThrow(() -> new IllegalArgumentException("StudyYear not found with id " + dto.getStudyYear().getId()));
            entity.setStudyYear(studyYear);
        }

        if (dto.getParentSubject() != null && dto.getParentSubject().getId() != null) {
            Subject parent = subjectRepository.findById(dto.getParentSubject().getId())
                .orElseThrow(() -> new IllegalArgumentException("Parent Subject not found with id " + dto.getParentSubject().getId()));
            entity.setParentSubject(parent);
        }
    }
}
