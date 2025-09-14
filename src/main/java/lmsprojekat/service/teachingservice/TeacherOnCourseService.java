package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
import lmsprojekat.model.Notification;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.model.teaching.TeachingType;
import lmsprojekat.model.users.Teacher;
import lmsprojekat.repository.NotificationRepository;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
import lmsprojekat.repository.teachingrepo.TeachingTypeRepository;
import lmsprojekat.repository.userrepo.TeacherRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TeacherOnCourseService extends AbstractCrudService<TeacherOnCourseDTO, TeacherOnCourse, Long> {

    private final TeacherOnCourseRepository repository;
    private final TeacherRepository teacherRepository;
    private final TeachingTypeRepository teachingTypeRepository;
    private final CourseRealizationRepository courseRealizationRepository;
    private final NotificationRepository notificationRepository;

    public TeacherOnCourseService(
            TeacherOnCourseRepository repository,
            TeacherRepository teacherRepository,
            TeachingTypeRepository teachingTypeRepository,
            CourseRealizationRepository courseRealizationRepository,
            NotificationRepository notificationRepository
    ) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.teachingTypeRepository = teachingTypeRepository;
        this.courseRealizationRepository = courseRealizationRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    protected TeacherOnCourseRepository getRepository() {
        return repository;
    }

    @Override
    public TeacherOnCourseDTO toDTO(TeacherOnCourse entity) {
        if (entity == null) return null;

        TeacherOnCourseDTO dto = new TeacherOnCourseDTO();
        dto.setId(entity.getId());
        dto.setNumberOfClasses(entity.getNumberOfClasses());
        dto.setTeacherId(entity.getTeacher() != null ? entity.getTeacher().getId() : null);
        dto.setTeachingTypeId(entity.getTeachingType() != null ? entity.getTeachingType().getId() : null);
        dto.setCourseRealizationId(entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null);
        dto.setNotificationIds(entity.getNotifications() != null
                ? entity.getNotifications().stream().map(Notification::getId).collect(Collectors.toList())
                : List.of());
        return dto;
    }

    @Override
    public TeacherOnCourse toEntity(TeacherOnCourseDTO dto) {
        if (dto == null) return null;

        TeacherOnCourse entity = new TeacherOnCourse();
        entity.setId(dto.getId());
        entity.setNumberOfClasses(dto.getNumberOfClasses());

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found id=" + dto.getTeacherId()));
            entity.setTeacher(teacher);
        }

        if (dto.getTeachingTypeId() != null) {
            TeachingType teachingType = teachingTypeRepository.findById(dto.getTeachingTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("TeachingType not found id=" + dto.getTeachingTypeId()));
            entity.setTeachingType(teachingType);
        }

        if (dto.getCourseRealizationId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealizationId()));
            entity.setCourseRealization(realization);
        }

        if (dto.getNotificationIds() != null) {
            List<Notification> notifications = dto.getNotificationIds().stream()
                    .map(id -> notificationRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Notification not found id=" + id)))
                    .collect(Collectors.toList());
            entity.setNotifications(notifications);
        }

        return entity;
    }

    @Override
    protected void updateEntity(TeacherOnCourse entity, TeacherOnCourseDTO dto) {
        if (dto.getNumberOfClasses() != null) {
            entity.setNumberOfClasses(dto.getNumberOfClasses());
        }

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found id=" + dto.getTeacherId()));
            entity.setTeacher(teacher);
        }

        if (dto.getTeachingTypeId() != null) {
            TeachingType teachingType = teachingTypeRepository.findById(dto.getTeachingTypeId())
                    .orElseThrow(() -> new EntityNotFoundException("TeachingType not found id=" + dto.getTeachingTypeId()));
            entity.setTeachingType(teachingType);
        }

        if (dto.getCourseRealizationId() != null) {
            CourseRealization realization = courseRealizationRepository.findById(dto.getCourseRealizationId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found id=" + dto.getCourseRealizationId()));
            entity.setCourseRealization(realization);
        }

        if (dto.getNotificationIds() != null) {
            List<Notification> notifications = dto.getNotificationIds().stream()
                    .map(id -> notificationRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("Notification not found id=" + id)))
                    .collect(Collectors.toList());
            entity.setNotifications(notifications);
        }
    }
}
