package lmsprojekat.repository;

import java.util.List;

import lmsprojekat.model.Notification;

public interface NotificationRepository extends SoftDeleteRepository<Notification, Long> {
    List<Notification> findByCourseRealizationId(Long courseId);
}
