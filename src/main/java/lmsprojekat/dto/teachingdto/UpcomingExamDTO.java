package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;

public class UpcomingExamDTO {
    private Long id;
    private String subjectName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String type;
    private boolean applied;

    public UpcomingExamDTO(Long id, String subjectName, LocalDateTime startTime,
                           LocalDateTime endTime, String type, boolean applied) {
        this.id = id;
        this.subjectName = subjectName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.applied = applied;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isApplied() {
		return applied;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

}
