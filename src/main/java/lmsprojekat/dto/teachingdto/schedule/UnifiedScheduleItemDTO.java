package lmsprojekat.dto.teachingdto.schedule;

import java.time.LocalDateTime;

public class UnifiedScheduleItemDTO {
	private ScheduleItemType type;
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long courseRealizationId;
	private String subjectName;
	private String label;

	public UnifiedScheduleItemDTO() {
	}

	public UnifiedScheduleItemDTO(ScheduleItemType type, Long id, LocalDateTime startTime, LocalDateTime endTime,
			Long courseRealizationId, String subjectName, String label) {
		this.type = type;
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseRealizationId = courseRealizationId;
		this.subjectName = subjectName;
		this.label = label;
	}

	public ScheduleItemType getType() {
		return type;
	}

	public void setType(ScheduleItemType type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
