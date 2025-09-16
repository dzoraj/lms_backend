package lmsprojekat.dto.teachingdto.schedule;

import java.time.LocalDateTime;

public class ConflictProbeDTO {
	private ScheduleItemType type;
	private Long courseRealizationId;
	private Long teacherId;
	private LocalDateTime start;
	private LocalDateTime end;

	public ConflictProbeDTO() {
	}

	public ConflictProbeDTO(ScheduleItemType type, Long courseRealizationId, Long teacherId, LocalDateTime start,
			LocalDateTime end) {
		super();
		this.type = type;
		this.courseRealizationId = courseRealizationId;
		this.teacherId = teacherId;
		this.start = start;
		this.end = end;
	}

	public ScheduleItemType getType() {
		return type;
	}

	public void setType(ScheduleItemType type) {
		this.type = type;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

}