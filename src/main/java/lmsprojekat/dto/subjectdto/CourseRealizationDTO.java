package lmsprojekat.dto.subjectdto;

import java.util.List;

public class CourseRealizationDTO {

    private Long id;
    private Long subjectId;
    private List<Long> teacherOnCourseIds;
    private List<Long> courseAttendanceIds;
    private List<Long> notificationIds;
    private List<Long> knowledgeEvaluationIds;
    private List<Long> teachingSessionIds;

    public CourseRealizationDTO() {}

    public CourseRealizationDTO(Long id, Long subjectId, List<Long> teacherOnCourseIds,
            List<Long> courseAttendanceIds, List<Long> notificationIds,
            List<Long> knowledgeEvaluationIds, List<Long> teachingSessionIds) {
        this.id = id;
        this.subjectId = subjectId;
        this.teacherOnCourseIds = teacherOnCourseIds;
        this.courseAttendanceIds = courseAttendanceIds;
        this.notificationIds = notificationIds;
        this.knowledgeEvaluationIds = knowledgeEvaluationIds;
        this.teachingSessionIds = teachingSessionIds;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public List<Long> getTeacherOnCourseIds() {
		return teacherOnCourseIds;
	}

	public void setTeacherOnCourseIds(List<Long> teacherOnCourseIds) {
		this.teacherOnCourseIds = teacherOnCourseIds;
	}

	public List<Long> getCourseAttendanceIds() {
		return courseAttendanceIds;
	}

	public void setCourseAttendanceIds(List<Long> courseAttendanceIds) {
		this.courseAttendanceIds = courseAttendanceIds;
	}

	public List<Long> getNotificationIds() {
		return notificationIds;
	}

	public void setNotificationIds(List<Long> notificationIds) {
		this.notificationIds = notificationIds;
	}

	public List<Long> getKnowledgeEvaluationIds() {
		return knowledgeEvaluationIds;
	}

	public void setKnowledgeEvaluationIds(List<Long> knowledgeEvaluationIds) {
		this.knowledgeEvaluationIds = knowledgeEvaluationIds;
	}

	public List<Long> getTeachingSessionIds() {
		return teachingSessionIds;
	}

	public void setTeachingSessionIds(List<Long> teachingSessionIds) {
		this.teachingSessionIds = teachingSessionIds;
	}

}
