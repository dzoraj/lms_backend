package lmsprojekat.model.quiz;

import jakarta.persistence.*;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class QuizOption extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "question_id")
	private QuizQuestion question;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private boolean correct;

	@Column(nullable = false)
	private Integer orderIndex;

	public QuizOption() {
	}

	public QuizOption(Long id, QuizQuestion question, String text, boolean correct, Integer orderIndex) {
		this.id = id;
		this.question = question;
		this.text = text;
		this.correct = correct;
		this.orderIndex = orderIndex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuizQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
