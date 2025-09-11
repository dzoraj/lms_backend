package lmsprojekat.dto.studentdto;

import java.util.List;

public class StudentProfileDTO {

	private Long id;
	private String name;
	private String email;

	private Double averageGrade;
	private Integer espb;

	private List<EnrollmentDTO> enrollments;
	private List<PassedExamDTO> passedExams;
	private List<ExamAttemptDTO> examAttempts;
	private List<FailedExamDTO> failedExams;
	private List<String> infractions;
	private List<String> registeredExams;
	private String thesis;
	private List<AttendingSubjectDTO> attendingSubjects;



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

	public static class ExamAttemptDTO {
		public String subjectName;
		public Integer espb;
		public Long evaluationId;
		public Integer points;
		public String note;
	}

	public static class FailedExamDTO {
		public String subjectName;
		public Integer attempts;
		public Integer lastPoints;
	}
	public static class AttendingSubjectDTO {
	    public Long subjectId;
	    public String name;
	    public Integer espb;
	    public Integer lectureCount;
	    public Integer labCount;
	    public Boolean mandatory;
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

	public Integer getEspb() {
		return espb;
	}

	public void setEspb(Integer espb) {
		this.espb = espb;
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

	public List<ExamAttemptDTO> getExamAttempts() {
		return examAttempts;
	}

	public void setExamAttempts(List<ExamAttemptDTO> examAttempts) {
		this.examAttempts = examAttempts;
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
	public List<AttendingSubjectDTO> getAttendingSubjects() {
	    return attendingSubjects;
	}

	public void setAttendingSubjects(List<AttendingSubjectDTO> attendingSubjects) {
	    this.attendingSubjects = attendingSubjects;
	}
}
