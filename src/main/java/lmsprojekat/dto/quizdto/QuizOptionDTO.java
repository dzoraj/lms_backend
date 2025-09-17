package lmsprojekat.dto.quizdto;

public class QuizOptionDTO {
	private Long id;
	private String text;
	private Boolean correct;
	private Integer orderIndex;

	public QuizOptionDTO() {
	}

	public QuizOptionDTO(Long id, String text, Boolean correct, Integer orderIndex) {
		this.id = id;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
