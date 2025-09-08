package lmsprojekat.dto.userdto;

import java.util.List;

import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.dto.studentdto.SubjectStudySummaryDTO;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;

public class StudentDashboardDTO {
	private List<CourseAttendanceDTO> currentCourses;
	private List<StudentInYearDTO> studyHistory;
	private List<SubjectStudySummaryDTO> studyHistoryCourses;

	public List<SubjectStudySummaryDTO> getStudyHistoryCourses() {
		return studyHistoryCourses;
	}

	public StudentDashboardDTO(List<CourseAttendanceDTO> currentCourses, List<StudentInYearDTO> studyHistory,
			List<SubjectStudySummaryDTO> studyHistoryCourses, Double averageGrade, Integer totalEspb) {
		super();
		this.currentCourses = currentCourses;
		this.studyHistory = studyHistory;
		this.studyHistoryCourses = studyHistoryCourses;
		this.averageGrade = averageGrade;
		this.totalEspb = totalEspb;
	}

	public void setStudyHistoryCourses(List<SubjectStudySummaryDTO> studyHistoryCourses) {
		this.studyHistoryCourses = studyHistoryCourses;
	}

	private Double averageGrade;
	private Integer totalEspb;

	public StudentDashboardDTO() {
	}

	public List<CourseAttendanceDTO> getCurrentCourses() {
		return currentCourses;
	}

	public void setCurrentCourses(List<CourseAttendanceDTO> currentCourses) {
		this.currentCourses = currentCourses;
	}

	public List<StudentInYearDTO> getStudyHistory() {
		return studyHistory;
	}

	public void setStudyHistory(List<StudentInYearDTO> studyHistory) {
		this.studyHistory = studyHistory;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public Integer getTotalEspb() {
		return totalEspb;
	}

	public void setTotalEspb(Integer totalEspb) {
		this.totalEspb = totalEspb;
	}

}
