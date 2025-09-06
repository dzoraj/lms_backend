package lmsprojekat.dto.subjectdto;

import java.util.List;

import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;

public class CourseRealizationDTO {

    private Long id;
    private SubjectDTO subject;
    private List<TeacherOnCourseDTO> teachersOnCourse;
    private List<CourseAttendanceDTO> courseAttendances;
    private List<NotificationDTO> notifications;
    private List<KnowledgeEvaluationDTO> knowledgeEvaluations;
    private List<TeachingSessionDTO> teachingSessions;

    public CourseRealizationDTO() {}

    public CourseRealizationDTO(Long id, SubjectDTO subject, List<TeacherOnCourseDTO> teachersOnCourse,
            List<CourseAttendanceDTO> courseAttendances, List<NotificationDTO> notifications,
            List<KnowledgeEvaluationDTO> knowledgeEvaluations, List<TeachingSessionDTO> teachingSessions) {
        this.id = id;
        this.subject = subject;
        this.teachersOnCourse = teachersOnCourse;
        this.courseAttendances = courseAttendances;
        this.notifications = notifications;
        this.knowledgeEvaluations = knowledgeEvaluations;
        this.teachingSessions = teachingSessions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SubjectDTO getSubject() { return subject; }
    public void setSubject(SubjectDTO subject) { this.subject = subject; }

    public List<TeacherOnCourseDTO> getTeachersOnCourse() { return teachersOnCourse; }
    public void setTeachersOnCourse(List<TeacherOnCourseDTO> teachersOnCourse) { this.teachersOnCourse = teachersOnCourse; }

    public List<CourseAttendanceDTO> getCourseAttendances() { return courseAttendances; }
    public void setCourseAttendances(List<CourseAttendanceDTO> courseAttendances) { this.courseAttendances = courseAttendances; }

    public List<NotificationDTO> getNotifications() { return notifications; }
    public void setNotifications(List<NotificationDTO> notifications) { this.notifications = notifications; }

    public List<KnowledgeEvaluationDTO> getKnowledgeEvaluations() { return knowledgeEvaluations; }
    public void setKnowledgeEvaluations(List<KnowledgeEvaluationDTO> knowledgeEvaluations) { this.knowledgeEvaluations = knowledgeEvaluations; }

    public List<TeachingSessionDTO> getTeachingSessions() { return teachingSessions; }
    public void setTeachingSessions(List<TeachingSessionDTO> teachingSessions) { this.teachingSessions = teachingSessions; }

}
