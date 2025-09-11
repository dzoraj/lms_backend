package lmsprojekat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public NotificationDTO create(@RequestBody NotificationDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/course/{courseId}")
    public List<NotificationDTO> getForCourse(@PathVariable Long courseId) {
        return service.getNotificationsForCourse(courseId);
    }
    @GetMapping("/student/{studentId}")
    public List<NotificationDTO> getForStudent(@PathVariable Long studentId) {
        return service.getNotificationsForStudent(studentId);
    }

}
