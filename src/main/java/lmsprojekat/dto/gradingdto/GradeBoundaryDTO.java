package lmsprojekat.dto.gradingdto;

public class GradeBoundaryDTO {


	private Long id;

	private Integer minPoints;

	private Integer gradeValue;

	private Long gradingSchemeId;
	
	public GradeBoundaryDTO() {
		super();
	}

	public GradeBoundaryDTO(Long id, Integer minPoints, Integer gradeValue, Long gradingSchemeId) {
		super();
		this.id = id;
		this.minPoints = minPoints;
		this.gradeValue = gradeValue;
		this.gradingSchemeId = gradingSchemeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(Integer minPoints) {
		this.minPoints = minPoints;
	}

	public Integer getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(Integer gradeValue) {
		this.gradeValue = gradeValue;
	}

	public Long getGradingSchemeId() {
		return gradingSchemeId;
	}

	public void setGradingSchemeId(Long gradingSchemeId) {
		this.gradingSchemeId = gradingSchemeId;
	}
}
