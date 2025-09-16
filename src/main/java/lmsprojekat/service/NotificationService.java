package lmsprojekat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.Notification;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.repository.NotificationRepository;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;

@Service
public class NotificationService extends AbstractCrudService<NotificationDTO, Notification, Long> {

    private final NotificationRepository notificationRepository;
    private final CourseRealizationRepository courseRealizationRepository;
    private final TeacherOnCourseRepository teacherOnCourseRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(
            NotificationRepository notificationRepository,
            CourseRealizationRepository courseRealizationRepository,
            TeacherOnCourseRepository teacherOnCourseRepository,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.notificationRepository = notificationRepository;
        this.courseRealizationRepository = courseRealizationRepository;
        this.teacherOnCourseRepository = teacherOnCourseRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    protected SoftDeleteRepository<Notification, Long> getRepository() {
        return notificationRepository;
    }

    @Override
    public NotificationDTO toDTO(Notification entity) {
        String courseName = null;
        if (entity.getCourseRealization() != null && entity.getCourseRealization().getSubject() != null) {
            courseName = entity.getCourseRealization().getSubject().getName();
        }
        return new NotificationDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getTimePosted(),
            entity.getCourseRealization() != null ? entity.getCourseRealization().getId() : null,
            entity.getTeacherOnCourse() != null ? entity.getTeacherOnCourse().getId() : null,
            entity.getAttachments() != null
                ? entity.getAttachments().stream().map(File::getId).collect(Collectors.toList())
                : List.of(),
            courseName
        );
    }


    @Override
    public Notification toEntity(NotificationDTO dto) {
        Notification entity = new Notification();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setTimePosted(LocalDateTime.now());

        if (dto.getCourseRealizationId() != null) {
            entity.setCourseRealization(fetchCourseRealization(dto.getCourseRealizationId()));
        }
        if (dto.getTeacherOnCourseId() != null) {
            entity.setTeacherOnCourse(fetchTeacherOnCourse(dto.getTeacherOnCourseId()));
        }

        return entity;
    }

    @Override
    protected void updateEntity(Notification entity, NotificationDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());

        if (dto.getCourseRealizationId() != null) {
            entity.setCourseRealization(fetchCourseRealization(dto.getCourseRealizationId()));
        } else {
            entity.setCourseRealization(null);
        }

        if (dto.getTeacherOnCourseId() != null) {
            entity.setTeacherOnCourse(fetchTeacherOnCourse(dto.getTeacherOnCourseId()));
        } else {
            entity.setTeacherOnCourse(null);
        }
    }

    @Override
    public NotificationDTO save(NotificationDTO dto) {
        Notification entity = toEntity(dto);
        entity = notificationRepository.save(entity);
        NotificationDTO savedDto = toDTO(entity);
        messagingTemplate.convertAndSend("/topic/notifications", savedDto);
        return savedDto;
    }

    public List<NotificationDTO> getNotificationsForCourse(Long courseId) {
        return notificationRepository.findByCourseRealizationId(courseId)
                .stream().map(this::toDTO).toList();
    }

    public List<NotificationDTO> getNotificationsForStudent(Long studentId) {
        return notificationRepository.findByStudentId(studentId)
                .stream().map(this::toDTO).toList();
    }

    public List<NotificationDTO> getNotificationsForTeacher(Long teacherId) {
        return notificationRepository.findAll()
                .stream()
                .filter(n -> n.getTeacherOnCourse() != null &&
                             n.getTeacherOnCourse().getTeacher().getId().equals(teacherId))
                .map(this::toDTO)
                .toList();
    }

    public NotificationDTO createForTeacher(Long teacherId, NotificationDTO dto) {
        if (!teacherOnCourseRepository.existsById(dto.getTeacherOnCourseId())) {
            throw new SecurityException("Teacher is not assigned to this course.");
        }
        Notification entity = toEntity(dto);
        entity.setTimePosted(LocalDateTime.now());
        entity = notificationRepository.save(entity);
        NotificationDTO savedDto = toDTO(entity);
        messagingTemplate.convertAndSend("/topic/notifications", savedDto);
        return savedDto;
    }

    public NotificationDTO createForTeacherAndSubject(Long teacherId, Long subjectId, NotificationDTO dto) {
        TeacherOnCourse toc = teacherOnCourseRepository.findByTeacherAndSubject(teacherId, subjectId)
            .orElseThrow(() -> new SecurityException("Teacher is not assigned to this subject."));

        Notification entity = new Notification();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setTimePosted(LocalDateTime.now());
        entity.setTeacherOnCourse(toc);
        entity.setCourseRealization(toc.getCourseRealization());

        entity = notificationRepository.save(entity);
        NotificationDTO savedDto = toDTO(entity);
        messagingTemplate.convertAndSend("/topic/notifications", savedDto);
        return savedDto;
    }



    public List<NotificationDTO> getNotificationsForTeacherAndSubject(Long teacherId, Long subjectId) {
        return notificationRepository.findByTeacherIdAndSubjectId(teacherId, subjectId)
                .stream().map(this::toDTO).toList();
    }

    private CourseRealization fetchCourseRealization(Long id) {
        return courseRealizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found with id: " + id));
    }

    private TeacherOnCourse fetchTeacherOnCourse(Long id) {
        return teacherOnCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherOnCourse not found with id: " + id));
    }
    public List<NotificationDTO> getGeneralNotifications() {
        return notificationRepository.findGeneral().stream().map(this::toDTO).toList();
    }

    public NotificationDTO createGeneral(NotificationDTO dto) {
        Notification entity = new Notification();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setTimePosted(LocalDateTime.now());
        entity.setCourseRealization(null);
        entity.setTeacherOnCourse(null);
        entity = notificationRepository.save(entity);
        NotificationDTO saved = toDTO(entity);
        messagingTemplate.convertAndSend("/topic/general-notifications", saved);
        messagingTemplate.convertAndSend("/topic/notifications", saved);
        return saved;
    }

}
