package lmsprojekat.dto.studentdto;


public class ExamGradeDTO {
    private Long examApplicationId; 
    private Integer grade;
    private String note;
	public ExamGradeDTO(Long examApplicationId, Integer grade, String note) {
		super();
		this.examApplicationId = examApplicationId;
		this.grade = grade;
		this.note = note;
	}
	public ExamGradeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getExamApplicationId() {
		return examApplicationId;
	}
	public void setExamApplicationId(Long examApplicationId) {
		this.examApplicationId = examApplicationId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

    
}
