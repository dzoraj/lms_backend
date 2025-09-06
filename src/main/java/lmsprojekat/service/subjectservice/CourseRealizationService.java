package lmsprojekat.service.subjectservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.NotificationService;
import lmsprojekat.service.teachingservice.KnowledgeEvaluationService;
import lmsprojekat.service.teachingservice.TeacherOnCourseService;
import lmsprojekat.service.teachingservice.TeachingSessionService;

@Service
@Transactional
public class CourseRealizationService extends AbstractCrudService<CourseRealizationDTO, CourseRealization, Long> {

    private final CourseRealizationRepository courseRealizationRepository;
    private final SubjectService subjectService;
    private final TeacherOnCourseService teacherOnCourseService;
    private final CourseAttendanceService courseAttendanceService;
    private final NotificationService notificationService;
    private final KnowledgeEvaluationService knowledgeEvaluationService;
    private final TeachingSessionService teachingSessionService;

    public CourseRealizationService(
        CourseRealizationRepository courseRealizationRepository,
        SubjectService subjectService,
        TeacherOnCourseService teacherOnCourseService,
        CourseAttendanceService courseAttendanceService,
        NotificationService notificationService,
        KnowledgeEvaluationService knowledgeEvaluationService,
        TeachingSessionService teachingSessionService
    ) {
        this.courseRealizationRepository = courseRealizationRepository;
        this.subjectService = subjectService;
        this.teacherOnCourseService = teacherOnCourseService;
        this.courseAttendanceService = courseAttendanceService;
        this.notificationService = notificationService;
        this.knowledgeEvaluationService = knowledgeEvaluationService;
        this.teachingSessionService = teachingSessionService;
    }

    @Override
    protected CourseRealizationRepository getRepository() {
        return courseRealizationRepository;
    }

    @Override
	public CourseRealizationDTO toDTO(CourseRealization entity) {
        if (entity == null) return null;

        return new CourseRealizationDTO(
            entity.getId(),
            subjectService.toDTO(entity.getSubject()),
            entity.getTeachersOnCourse() == null ? null :
                entity.getTeachersOnCourse().stream()
                    .map(teacherOnCourseService::toDTO)
                    .collect(Collectors.toList()),
            entity.getCourseAttendances() == null ? null :
                entity.getCourseAttendances().stream()
                    .map(courseAttendanceService::toDTO)
                    .collect(Collectors.toList()),
            entity.getNotifications() == null ? null :
                entity.getNotifications().stream()
                    .map(notificationService::toDTO)
                    .collect(Collectors.toList()),
            entity.getKnowledgeEvaluations() == null ? null :
                entity.getKnowledgeEvaluations().stream()
                    .map(knowledgeEvaluationService::toDTO)
                    .collect(Collectors.toList()),
            entity.getTeachingSessions() == null ? null :
                entity.getTeachingSessions().stream()
                    .map(teachingSessionService::toDTO)
                    .collect(Collectors.toList())
        );
    }

    @Override
    public CourseRealization toEntity(CourseRealizationDTO dto) {
        if (dto == null) return null;

        CourseRealization entity = new CourseRealization();

        entity.setId(dto.getId());

        if (dto.getSubject() == null || dto.getSubject().getId() == null) {
            throw new IllegalArgumentException("Subject must be provided in CourseRealizationDTO");
        }
        entity.setSubject(subjectService.getRepository().findById(dto.getSubject().getId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + dto.getSubject().getId())));

        entity.setTeachersOnCourse(dto.getTeachersOnCourse() == null ? List.of() :
            dto.getTeachersOnCourse().stream()
                .map(tocDTO -> teacherOnCourseService.toEntity(tocDTO))
                .collect(Collectors.toList()));

        entity.setCourseAttendances(dto.getCourseAttendances() == null ? List.of() :
            dto.getCourseAttendances().stream()
                .map(caDTO -> courseAttendanceService.toEntity(caDTO))
                .collect(Collectors.toList()));

        entity.setNotifications(dto.getNotifications() == null ? List.of() :
            dto.getNotifications().stream()
                .map(notifDTO -> notificationService.toEntity(notifDTO))
                .collect(Collectors.toList()));

        entity.setKnowledgeEvaluations(dto.getKnowledgeEvaluations() == null ? List.of() :
            dto.getKnowledgeEvaluations().stream()
                .map(keDTO -> knowledgeEvaluationService.toEntity(keDTO))
                .collect(Collectors.toList()));

        entity.setTeachingSessions(dto.getTeachingSessions() == null ? List.of() :
            dto.getTeachingSessions().stream()
                .map(tsDTO -> teachingSessionService.toEntity(tsDTO))
                .collect(Collectors.toList()));

        return entity;
    }

    @Override
    protected void updateEntity(CourseRealization entity, CourseRealizationDTO dto) {
        if (dto == null) return;

        if (dto.getSubject() != null && dto.getSubject().getId() != null) {
            entity.setSubject(subjectService.getRepository().findById(dto.getSubject().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + dto.getSubject().getId())));
        }


        entity.setTeachersOnCourse(dto.getTeachersOnCourse() == null ? List.of() :
            dto.getTeachersOnCourse().stream()
                .map(tocDTO -> teacherOnCourseService.toEntity(tocDTO))
                .collect(Collectors.toList()));

        entity.setCourseAttendances(dto.getCourseAttendances() == null ? List.of() :
            dto.getCourseAttendances().stream()
                .map(caDTO -> courseAttendanceService.toEntity(caDTO))
                .collect(Collectors.toList()));

        entity.setNotifications(dto.getNotifications() == null ? List.of() :
            dto.getNotifications().stream()
                .map(notifDTO -> notificationService.toEntity(notifDTO))
                .collect(Collectors.toList()));

        entity.setKnowledgeEvaluations(dto.getKnowledgeEvaluations() == null ? List.of() :
            dto.getKnowledgeEvaluations().stream()
                .map(keDTO -> knowledgeEvaluationService.toEntity(keDTO))
                .collect(Collectors.toList()));

        entity.setTeachingSessions(dto.getTeachingSessions() == null ? List.of() :
            dto.getTeachingSessions().stream()
                .map(tsDTO -> teachingSessionService.toEntity(tsDTO))
                .collect(Collectors.toList()));
    }
}
