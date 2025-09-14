package lmsprojekat.service.subjectservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.gradingrepo.GradingSchemeRepository;
import lmsprojekat.repository.studentrepo.StudyYearRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.service.AbstractCrudService;
@Service
public class SubjectService extends AbstractCrudService<SubjectDTO, Subject, Long> {

    private final SubjectRepository subjectRepository;
    private final StudyYearRepository studyYearRepository;
    private final GradingSchemeRepository gradingSchemeRepository;

    public SubjectService(
            SubjectRepository subjectRepository,
            StudyYearRepository studyYearRepository,
            GradingSchemeRepository gradingSchemeRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.studyYearRepository = studyYearRepository;
        this.gradingSchemeRepository = gradingSchemeRepository;
    }

    @Override
    protected SubjectRepository getRepository() {
        return subjectRepository;
    }

    @Override
    public SubjectDTO toDTO(Subject entity) {
        if (entity == null) return null;

        SubjectDTO dto = new SubjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEspb(entity.getEspb());
        dto.setMandatory(entity.getMandatory());
        dto.setLectureCount(entity.getLectureCount());
        dto.setLabCount(entity.getLabCount());
        dto.setOtherTeachingForms(entity.getOtherTeachingForms());
        dto.setResearchWork(entity.getResearchWork());
        dto.setOtherClasses(entity.getOtherClasses());

        dto.setStudyYearId(entity.getStudyYear() != null ? entity.getStudyYear().getId() : null);
        dto.setParentSubjectId(entity.getParentSubject() != null ? entity.getParentSubject().getId() : null);
        dto.setGradingSchemeId(entity.getGradingScheme() != null ? entity.getGradingScheme().getId() : null);

        dto.setSyllabusIds(entity.getSyllabus() != null
                ? entity.getSyllabus().stream().map(s -> s.getId()).toList()
                : List.of());

        dto.setSubSubjectIds(entity.getSubSubjects() != null
                ? entity.getSubSubjects().stream().map(s -> s.getId()).toList()
                : List.of());

        return dto;
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

        if (dto.getStudyYearId() != null) {
            entity.setStudyYear(studyYearRepository.findById(dto.getStudyYearId())
                    .orElseThrow(() -> new IllegalArgumentException("StudyYear not found id=" + dto.getStudyYearId())));
        }

        if (dto.getParentSubjectId() != null) {
            entity.setParentSubject(subjectRepository.findById(dto.getParentSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent Subject not found id=" + dto.getParentSubjectId())));
        }

        if (dto.getGradingSchemeId() != null) {
            entity.setGradingScheme(gradingSchemeRepository.findById(dto.getGradingSchemeId())
                    .orElseThrow(() -> new IllegalArgumentException("GradingScheme not found id=" + dto.getGradingSchemeId())));
        }

        entity.setSyllabus(new ArrayList<>());
        entity.setSubSubjects(new ArrayList<>());

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

        if (dto.getStudyYearId() != null) {
            entity.setStudyYear(studyYearRepository.findById(dto.getStudyYearId())
                    .orElseThrow(() -> new IllegalArgumentException("StudyYear not found id=" + dto.getStudyYearId())));
        }

        if (dto.getParentSubjectId() != null) {
            entity.setParentSubject(subjectRepository.findById(dto.getParentSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent Subject not found id=" + dto.getParentSubjectId())));
        }

        if (dto.getGradingSchemeId() != null) {
            entity.setGradingScheme(gradingSchemeRepository.findById(dto.getGradingSchemeId())
                    .orElseThrow(() -> new IllegalArgumentException("GradingScheme not found id=" + dto.getGradingSchemeId())));
        }
    }
}
