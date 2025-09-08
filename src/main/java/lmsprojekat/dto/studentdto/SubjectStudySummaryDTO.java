package lmsprojekat.dto.studentdto;

public class SubjectStudySummaryDTO {
	private Long subjectId;
	private String subjectName;
	private Integer espb;
	private int attempts;
	private Integer finalPoints;
	private Integer finalGrade;

	public SubjectStudySummaryDTO() {
	}

	public SubjectStudySummaryDTO(Long subjectId, String subjectName, Integer espb, int attempts, Integer finalPoints,
			Integer finalGrade) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.espb = espb;
		this.attempts = attempts;
		this.finalPoints = finalPoints;
		this.finalGrade = finalGrade;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getEspb() {
		return espb;
	}

	public void setEspb(Integer espb) {
		this.espb = espb;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Integer getFinalPoints() {
		return finalPoints;
	}

	public void setFinalPoints(Integer finalPoints) {
		this.finalPoints = finalPoints;
	}

	public Integer getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(Integer finalGrade) {
		this.finalGrade = finalGrade;
	}
}
