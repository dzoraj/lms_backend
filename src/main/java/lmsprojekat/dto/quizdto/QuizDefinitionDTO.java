package lmsprojekat.dto.quizdto;

import java.util.List;

public class QuizDefinitionDTO {
	private Long id;
	private Long knowledgeEvaluationId;
	private String title;
	private String instructions;
	private Integer maxPoints;
	private Boolean active;
	private List<QuizQuestionDTO> questions;

	public QuizDefinitionDTO() {
	}

	public QuizDefinitionDTO(Long id, Long knowledgeEvaluationId, String title, String instructions, Integer maxPoints,
			Boolean active, List<QuizQuestionDTO> questions) {
		this.id = id;
		this.knowledgeEvaluationId = knowledgeEvaluationId;
		this.title = title;
		this.instructions = instructions;
		this.maxPoints = maxPoints;
		this.active = active;
		this.questions = questions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKnowledgeEvaluationId() {
		return knowledgeEvaluationId;
	}

	public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) {
		this.knowledgeEvaluationId = knowledgeEvaluationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Integer getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<QuizQuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuizQuestionDTO> questions) {
		this.questions = questions;
	}
}
