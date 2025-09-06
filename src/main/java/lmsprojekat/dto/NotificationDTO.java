package lmsprojekat.dto;

import java.time.LocalDateTime;
import java.util.List;

import lmsprojekat.dto.subjectdto.CourseRealizationDTO;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;

public class NotificationDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime timePosted;
    private CourseRealizationDTO courseRealization;
    private TeacherOnCourseDTO teacherOnCourse;
    private List<FileDTO> attachments;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String title, String content, LocalDateTime timePosted,
                           CourseRealizationDTO courseRealization, TeacherOnCourseDTO teacherOnCourse,
                           List<FileDTO> attachments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timePosted = timePosted;
        this.courseRealization = courseRealization;
        this.teacherOnCourse = teacherOnCourse;
        this.attachments = attachments;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getTimePosted() { return timePosted; }
    public void setTimePosted(LocalDateTime timePosted) { this.timePosted = timePosted; }

    public CourseRealizationDTO getCourseRealization() { return courseRealization; }
    public void setCourseRealization(CourseRealizationDTO courseRealization) { this.courseRealization = courseRealization; }

    public TeacherOnCourseDTO getTeacherOnCourse() { return teacherOnCourse; }
    public void setTeacherOnCourse(TeacherOnCourseDTO teacherOnCourse) { this.teacherOnCourse = teacherOnCourse; }

    public List<FileDTO> getAttachments() { return attachments; }
    public void setAttachments(List<FileDTO> attachments) { this.attachments = attachments; }
}
