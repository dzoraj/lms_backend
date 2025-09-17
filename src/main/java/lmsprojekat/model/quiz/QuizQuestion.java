package lmsprojekat.model.quiz;

import jakarta.persistence.*;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class QuizQuestion extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "quiz_definition_id")
	private QuizDefinition quiz;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	private Integer points;

	@Column(nullable = false)
	private Integer orderIndex;

	public QuizQuestion() {
	}

	public QuizQuestion(Long id, QuizDefinition quiz, String text, String type, Integer points, Integer orderIndex) {
		this.id = id;
		this.quiz = quiz;
		this.text = text;
		this.type = type;
		this.points = points;
		this.orderIndex = orderIndex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuizDefinition getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizDefinition quiz) {
		this.quiz = quiz;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
