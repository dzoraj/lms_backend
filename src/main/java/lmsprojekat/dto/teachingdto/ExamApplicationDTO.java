package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;

public class ExamApplicationDTO {
	private Long id;
	private LocalDateTime applicationDate;
	private Long studentInYearId;
	private Long knowledgeEvaluationId;

	public ExamApplicationDTO() {
	}

	public ExamApplicationDTO(Long id, LocalDateTime applicationDate, Long studentInYearId,
			Long knowledgeEvaluationId) {
		this.id = id;
		this.applicationDate = applicationDate;
		this.studentInYearId = studentInYearId;
		this.knowledgeEvaluationId = knowledgeEvaluationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Long getStudentInYearId() {
		return studentInYearId;
	}

	public void setStudentInYearId(Long studentInYearId) {
		this.studentInYearId = studentInYearId;
	}

	public Long getKnowledgeEvaluationId() {
		return knowledgeEvaluationId;
	}

	public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) {
		this.knowledgeEvaluationId = knowledgeEvaluationId;
	}
}
