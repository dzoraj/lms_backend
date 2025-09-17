package lmsprojekat.dto.quizdto;

import java.util.List;

public class QuizQuestionDTO {
	private Long id;
	private String text;
	private String type;
	private Integer points;
	private Integer orderIndex;
	private List<QuizOptionDTO> options;

	public QuizQuestionDTO() {
	}

	public QuizQuestionDTO(Long id, String text, String type, Integer points, Integer orderIndex,
			List<QuizOptionDTO> options) {
		this.id = id;
		this.text = text;
		this.type = type;
		this.points = points;
		this.orderIndex = orderIndex;
		this.options = options;
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

	public List<QuizOptionDTO> getOptions() {
		return options;
	}

	public void setOptions(List<QuizOptionDTO> options) {
		this.options = options;
	}
}
