package lmsprojekat.dto.teachingdto;

import java.util.List;

public class TeacherOnCourseDTO {
	private Long id;
	private Integer numberOfClasses;
	private Long teacherId;
	private Long teachingTypeId;
	private Long courseRealizationId;
	private List<Long> notificationIds;

	public TeacherOnCourseDTO() {
	}

	public TeacherOnCourseDTO(Long id, Integer numberOfClasses, Long teacherId, Long teachingTypeId,
			Long courseRealizationId, List<Long> notificationIds) {
		this.id = id;
		this.numberOfClasses = numberOfClasses;
		this.teacherId = teacherId;
		this.teachingTypeId = teachingTypeId;
		this.courseRealizationId = courseRealizationId;
		this.notificationIds = notificationIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(Integer numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getTeachingTypeId() {
		return teachingTypeId;
	}

	public void setTeachingTypeId(Long teachingTypeId) {
		this.teachingTypeId = teachingTypeId;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public List<Long> getNotificationIds() {
		return notificationIds;
	}

	public void setNotificationIds(List<Long> notificationIds) {
		this.notificationIds = notificationIds;
	}

}
