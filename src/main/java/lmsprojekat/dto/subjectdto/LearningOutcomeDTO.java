package lmsprojekat.dto.subjectdto;

import java.util.List;

public class LearningOutcomeDTO {

    private Long id;
    private String description;
    private Long subjectId;
    private List<Long> educationalGoalIds;
    private List<Long> teachingMaterialIds;
    private List<Long> knowledgeEvaluationIds;
    private List<Long> teachingSessionIds;

    public LearningOutcomeDTO() {}

    public LearningOutcomeDTO(Long id, String description, Long subjectId,
            List<Long> educationalGoalIds, List<Long> teachingMaterialIds,
            List<Long> knowledgeEvaluationIds, List<Long> teachingSessionIds) {
        this.id = id;
        this.description = description;
        this.subjectId = subjectId;
        this.educationalGoalIds = educationalGoalIds;
        this.teachingMaterialIds = teachingMaterialIds;
        this.knowledgeEvaluationIds = knowledgeEvaluationIds;
        this.teachingSessionIds = teachingSessionIds;
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

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public List<Long> getEducationalGoalIds() {
		return educationalGoalIds;
	}

	public void setEducationalGoalIds(List<Long> educationalGoalIds) {
		this.educationalGoalIds = educationalGoalIds;
	}

	public List<Long> getTeachingMaterialIds() {
		return teachingMaterialIds;
	}

	public void setTeachingMaterialIds(List<Long> teachingMaterialIds) {
		this.teachingMaterialIds = teachingMaterialIds;
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
