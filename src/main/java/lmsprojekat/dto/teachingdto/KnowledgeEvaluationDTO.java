package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;
import java.util.List;

public class KnowledgeEvaluationDTO {
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Integer points;
	private Long evaluationInstrumentId;
	private Long evaluationTypeId;
	private Long courseRealizationId;
	private List<Long> learningOutcomeIds;

	public KnowledgeEvaluationDTO() {
	}

	public KnowledgeEvaluationDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer points,
			Long evaluationInstrumentId, Long evaluationTypeId, Long courseRealizationId,
			List<Long> learningOutcomeIds) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.evaluationInstrumentId = evaluationInstrumentId;
		this.evaluationTypeId = evaluationTypeId;
		this.courseRealizationId = courseRealizationId;
		this.learningOutcomeIds = learningOutcomeIds;
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Long getEvaluationInstrumentId() {
		return evaluationInstrumentId;
	}

	public void setEvaluationInstrumentId(Long evaluationInstrumentId) {
		this.evaluationInstrumentId = evaluationInstrumentId;
	}

	public Long getEvaluationTypeId() {
		return evaluationTypeId;
	}

	public void setEvaluationTypeId(Long evaluationTypeId) {
		this.evaluationTypeId = evaluationTypeId;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public List<Long> getLearningOutcomeIds() {
		return learningOutcomeIds;
	}

	public void setLearningOutcomeIds(List<Long> learningOutcomeIds) {
		this.learningOutcomeIds = learningOutcomeIds;
	}

}
