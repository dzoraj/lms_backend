package lmsprojekat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.FileDTO;
import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
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
    public NotificationDTO toDTO(Notification notification) {
        return new NotificationDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getTimePosted(),
                toCourseRealizationDTO(notification.getCourseRealization()),
                toTeacherOnCourseDTO(notification.getTeacherOnCourse()),
                notification.getAttachments().stream().map(this::toFileDTO).collect(Collectors.toList())
        );
    }

    @Override
	public Notification toEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setId(dto.getId());
        notification.setTitle(dto.getTitle());
        notification.setContent(dto.getContent());
        notification.setTimePosted(LocalDateTime.now()); 
        notification.setCourseRealization(fetchCourseRealization(dto.getCourseRealization().getId()));
        notification.setTeacherOnCourse(fetchTeacherOnCourse(dto.getTeacherOnCourse().getId()));
        return notification;
    }

    @Override
    protected void updateEntity(Notification entity, NotificationDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCourseRealization(fetchCourseRealization(dto.getCourseRealization().getId()));
        entity.setTeacherOnCourse(fetchTeacherOnCourse(dto.getTeacherOnCourse().getId()));
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
                .stream().map(this::toDTO).collect(Collectors.toList());
    }


    private CourseRealizationDTO toCourseRealizationDTO(CourseRealization cr) {
        CourseRealizationDTO dto = new CourseRealizationDTO();
        dto.setId(cr.getId());
        return dto;
    }

    private TeacherOnCourseDTO toTeacherOnCourseDTO(TeacherOnCourse toc) {
        TeacherOnCourseDTO dto = new TeacherOnCourseDTO();
        dto.setId(toc.getId());
        return dto;
    }

    private FileDTO toFileDTO(File file) {
        FileDTO dto = new FileDTO();
        dto.setId(file.getId());
        dto.setDescription(file.getDescription());
        dto.setUrl(file.getUrl());
        return dto;
    }


    private CourseRealization fetchCourseRealization(Long id) {
        return courseRealizationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found with id: " + id));
    }

    private TeacherOnCourse fetchTeacherOnCourse(Long id) {
        return teacherOnCourseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherOnCourse not found with id: " + id));
    }
    public List<NotificationDTO> getNotificationsForStudent(Long studentId) {
        return notificationRepository.findByStudentId(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
