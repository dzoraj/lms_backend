package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;

public class ExamApplicationDTO {
    private Long id;
    private LocalDateTime applicationDate;
    private Long studentInYearId;
    private Long knowledgeEvaluationId;
    private String studentName;
    private String indexNumber;
    private LocalDateTime examDate;
    private String evaluationType;
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
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public LocalDateTime getExamDate() {
		return examDate;
	}
	public void setExamDate(LocalDateTime examDate) {
		this.examDate = examDate;
	}
	public String getEvaluationType() {
		return evaluationType;
	}
	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}
	public ExamApplicationDTO(Long id, LocalDateTime applicationDate, Long studentInYearId, Long knowledgeEvaluationId,
			String studentName, String indexNumber, LocalDateTime examDate, String evaluationType) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.studentInYearId = studentInYearId;
		this.knowledgeEvaluationId = knowledgeEvaluationId;
		this.studentName = studentName;
		this.indexNumber = indexNumber;
		this.examDate = examDate;
		this.evaluationType = evaluationType;
	}
	public ExamApplicationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
