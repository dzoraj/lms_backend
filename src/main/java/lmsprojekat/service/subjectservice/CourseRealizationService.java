package lmsprojekat.service.subjectservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
@Transactional
public class CourseRealizationService extends AbstractCrudService<CourseRealizationDTO, CourseRealization, Long> {

    private final CourseRealizationRepository courseRealizationRepository;
    private final SubjectRepository subjectRepository;

    public CourseRealizationService(
        CourseRealizationRepository courseRealizationRepository,
        SubjectRepository subjectRepository
    ) {
        this.courseRealizationRepository = courseRealizationRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    protected CourseRealizationRepository getRepository() {
        return courseRealizationRepository;
    }

    @Override
    public CourseRealizationDTO toDTO(CourseRealization entity) {
        if (entity == null) return null;

        CourseRealizationDTO dto = new CourseRealizationDTO();
        dto.setId(entity.getId());
        dto.setSubjectId(entity.getSubject() != null ? entity.getSubject().getId() : null);

        dto.setTeacherOnCourseIds(entity.getTeachersOnCourse() == null ? List.of() :
            entity.getTeachersOnCourse().stream().map(toc -> toc.getId()).collect(Collectors.toList()));

        dto.setCourseAttendanceIds(entity.getCourseAttendances() == null ? List.of() :
            entity.getCourseAttendances().stream().map(ca -> ca.getId()).collect(Collectors.toList()));

        dto.setNotificationIds(entity.getNotifications() == null ? List.of() :
            entity.getNotifications().stream().map(n -> n.getId()).collect(Collectors.toList()));

        dto.setKnowledgeEvaluationIds(entity.getKnowledgeEvaluations() == null ? List.of() :
            entity.getKnowledgeEvaluations().stream().map(ke -> ke.getId()).collect(Collectors.toList()));

        dto.setTeachingSessionIds(entity.getTeachingSessions() == null ? List.of() :
            entity.getTeachingSessions().stream().map(ts -> ts.getId()).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public CourseRealization toEntity(CourseRealizationDTO dto) {
        if (dto == null) return null;

        CourseRealization entity = new CourseRealization();
        entity.setId(dto.getId());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found id=" + dto.getSubjectId()));
            entity.setSubject(subject);
        }

        entity.setTeachersOnCourse(List.of());
        entity.setCourseAttendances(List.of());
        entity.setNotifications(List.of());
        entity.setKnowledgeEvaluations(List.of());
        entity.setTeachingSessions(List.of());

        return entity;
    }

    @Override
    protected void updateEntity(CourseRealization entity, CourseRealizationDTO dto) {
        if (dto == null) return;

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found id=" + dto.getSubjectId()));
            entity.setSubject(subject);
        }

    }
}
