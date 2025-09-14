package lmsprojekat.dto.teachingdto;

public class EvaluationAttemptDTO {
	private Long id;
	private Integer points;
	private String note;
    private boolean isLatest; 
	private Long evaluationId;
	private Long studentInYearId;

	public EvaluationAttemptDTO() {
	}

	public EvaluationAttemptDTO(Long id, Integer points, String note, boolean isLatest,Long evaluationId, Long studentInYearId) {
		this.id = id;
		this.points = points;
		this.note = note;
		this.isLatest= isLatest;
		this.evaluationId = evaluationId;
		this.studentInYearId = studentInYearId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	



	public boolean isLatest() {
		return isLatest;
	}

	public void setLatest(boolean isLatest) {
		this.isLatest = isLatest;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public Long getStudentInYearId() {
		return studentInYearId;
	}

	public void setStudentInYearId(Long studentInYearId) {
		this.studentInYearId = studentInYearId;
	}
}
