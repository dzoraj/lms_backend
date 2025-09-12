package lmsprojekat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/notifications")
    public NotificationDTO create(@RequestBody NotificationDTO dto) {
        return service.save(dto);
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
}
