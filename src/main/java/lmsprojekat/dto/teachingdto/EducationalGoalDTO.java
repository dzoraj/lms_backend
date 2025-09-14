package lmsprojekat.dto.teachingdto;

import java.util.List;

public class EducationalGoalDTO {
	private Long id;
	private String description;
	private List<Long> learningOutcomeIds;

	public EducationalGoalDTO() {
	}

	public EducationalGoalDTO(Long id, String description, List<Long> learningOutcomeIds) {
		this.id = id;
		this.description = description;
		this.learningOutcomeIds = learningOutcomeIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getLearningOutcomeIds() {
		return learningOutcomeIds;
	}

	public void setLearningOutcomeIds(List<Long> learningOutcomeIds) {
		this.learningOutcomeIds = learningOutcomeIds;
	}
}
