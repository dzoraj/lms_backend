
package lmsprojekat.dto.studentdto;

import java.util.List;

public class StudentProfileDTO {

	private Long id;
	private String name;
	private String email;

	private Double averageGrade;
	private Integer ects;

	private List<EnrollmentDTO> enrollments;
	private List<PassedExamDTO> passedExams;
	private List<FailedExamDTO> failedExams;
	private List<String> infractions;
	private List<String> registeredExams;
	private String thesis;

	public static class EnrollmentDTO {
		public Long id;
		public String enrollmentDate;
		public String indexNumber;
		public Long studyYearId;
	}

	public static class PassedExamDTO {
		public String subjectName;
		public Integer espb;
		public Integer grade;
		public Integer finalPoints;
	}

	public static class FailedExamDTO {
		public String subjectName;
		public Integer attempts;
		public Integer lastPoints;
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

	public List<EnrollmentDTO> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<EnrollmentDTO> enrollments) {
		this.enrollments = enrollments;
	}

	public List<PassedExamDTO> getPassedExams() {
		return passedExams;
	}

	public void setPassedExams(List<PassedExamDTO> passedExams) {
		this.passedExams = passedExams;
	}

	public List<FailedExamDTO> getFailedExams() {
		return failedExams;
	}

	public void setFailedExams(List<FailedExamDTO> failedExams) {
		this.failedExams = failedExams;
	}

	public List<String> getInfractions() {
		return infractions;
	}

	public void setInfractions(List<String> infractions) {
		this.infractions = infractions;
	}

	public List<String> getRegisteredExams() {
		return registeredExams;
	}

	public void setRegisteredExams(List<String> registeredExams) {
		this.registeredExams = registeredExams;
	}

	public String getThesis() {
		return thesis;
	}

	public void setThesis(String thesis) {
		this.thesis = thesis;
	}
	
	

}
