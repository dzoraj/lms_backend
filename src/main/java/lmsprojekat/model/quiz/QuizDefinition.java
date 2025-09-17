package lmsprojekat.model.quiz;

import jakarta.persistence.*;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.teaching.KnowledgeEvaluation;

@Entity
public class QuizDefinition extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String title;

	@Column
	private String instructions;

	@ManyToOne(optional = false)
	@JoinColumn(name = "knowledge_evaluation_id")
	private KnowledgeEvaluation knowledgeEvaluation;

	@Column(nullable = false)
	private boolean active = true;

	public QuizDefinition() {
	}

	public QuizDefinition(Long id, String title, String instructions, KnowledgeEvaluation knowledgeEvaluation,
			boolean active) {
		this.id = id;
		this.title = title;
		this.instructions = instructions;
		this.knowledgeEvaluation = knowledgeEvaluation;
		this.active = active;
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

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public KnowledgeEvaluation getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(KnowledgeEvaluation knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
