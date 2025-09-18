package lmsprojekat.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController extends BaseCrudController<NotificationDTO, Long> {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @Override
    protected NotificationService getService() {
        return service;
    }

    @Override
    @GetMapping("/notifications")
    public List<NotificationDTO> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/notifications/{id}")
    public NotificationDTO findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/notifications")
    public NotificationDTO save(@RequestBody NotificationDTO dto) {
        return super.save(dto);
    }

    @Override
    @PutMapping("/notifications/{id}")
    public NotificationDTO update(@PathVariable Long id, @RequestBody NotificationDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @DeleteMapping("/notifications/{id}")
    public void delete(@PathVariable Long id) {
        super.delete(id);
    }

    @GetMapping("/notifications/course/{courseId}")
    public List<NotificationDTO> getForCourse(@PathVariable Long courseId) {
        return service.getNotificationsForCourse(courseId);
    }

    @GetMapping("/notifications/student/{studentId}")
    public List<NotificationDTO> getForStudent(@PathVariable Long studentId) {
        return service.getNotificationsForStudent(studentId);
    }

    @GetMapping("/notifications/{teacherId}/notifications")
    public List<NotificationDTO> getTeacherNotifications(@PathVariable Long teacherId) {
        return service.getNotificationsForTeacher(teacherId);
    }

    @PostMapping("/notifications/{teacherId}/notifications")
    public NotificationDTO createTeacherNotification(
            @PathVariable Long teacherId,
            @RequestBody NotificationDTO dto
    ) {
        return service.createForTeacher(teacherId, dto);
    }

    @PostMapping("/teacher/{teacherId}/subjects/{subjectId}/notifications")
    public NotificationDTO createForTeacherAndSubject(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @RequestBody NotificationDTO dto
    ) {
        return service.createForTeacherAndSubject(teacherId, subjectId, dto);
    }

    @GetMapping("/teacher/{teacherId}/subjects/{subjectId}/notifications")
    public List<NotificationDTO> getForTeacherAndSubject(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId
    ) {
        return service.getNotificationsForTeacherAndSubject(teacherId, subjectId);
    }

    @GetMapping("/notifications/general")
    public List<NotificationDTO> listGeneral() {
        return service.getGeneralNotifications();
    }

    @PostMapping("/notifications/general")
    @PreAuthorize("hasAnyRole('SA','ADMIN')")
    public NotificationDTO createGeneral(@RequestBody NotificationDTO dto) {
        dto.setCourseRealizationId(null);
        dto.setTeacherOnCourseId(null);
        return service.createGeneral(dto);
    }
}