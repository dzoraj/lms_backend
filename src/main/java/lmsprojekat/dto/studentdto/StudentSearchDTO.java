package lmsprojekat.dto.studentdto;

public class StudentSearchDTO {
	private Long id;
	private String name;
	private String email;
	private String indexNumber;
	private Integer enrollmentYear;
	private Double averageGrade;
	private Integer ects;

	public StudentSearchDTO() {
	}

	public StudentSearchDTO(Long id, String name, String email, String indexNumber, Integer enrollmentYear,
			Double averageGrade, Integer ects) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.indexNumber = indexNumber;
		this.enrollmentYear = enrollmentYear;
		this.averageGrade = averageGrade;
		this.ects = ects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Integer getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(Integer enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Integer getEcts() {
		return ects;
	}

	public void setEcts(Integer ects) {
		this.ects = ects;
	}

}
