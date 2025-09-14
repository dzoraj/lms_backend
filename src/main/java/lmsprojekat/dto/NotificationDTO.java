package lmsprojekat.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDTO {
	private Long id;
	private String title;
	private String content;
	private LocalDateTime timePosted;

	private Long courseRealizationId;
	private Long teacherOnCourseId;
	private List<Long> attachmentIds;

	public NotificationDTO() {
	}

	public NotificationDTO(Long id, String title, String content, LocalDateTime timePosted, Long courseRealizationId,
			Long teacherOnCourseId, List<Long> attachmentIds) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.courseRealizationId = courseRealizationId;
		this.teacherOnCourseId = teacherOnCourseId;
		this.attachmentIds = attachmentIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(LocalDateTime timePosted) {
		this.timePosted = timePosted;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public Long getTeacherOnCourseId() {
		return teacherOnCourseId;
	}

	public void setTeacherOnCourseId(Long teacherOnCourseId) {
		this.teacherOnCourseId = teacherOnCourseId;
	}

	public List<Long> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<Long> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

}
