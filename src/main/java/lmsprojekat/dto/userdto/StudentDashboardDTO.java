package lmsprojekat.dto.userdto;

import java.util.List;

import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.dto.studentdto.StudentInYearDTO;

public class StudentDashboardDTO {
    private List<CourseAttendanceDTO> currentCourses;
    private List<StudentInYearDTO> studyHistory;
    private Double averageGrade;
    private Integer totalEspb;

    public StudentDashboardDTO() {}

    public StudentDashboardDTO(List<CourseAttendanceDTO> currentCourses, List<StudentInYearDTO> studyHistory,
            Double averageGrade, Integer totalEspb) {
        this.currentCourses = currentCourses;
        this.studyHistory = studyHistory;
        this.averageGrade = averageGrade;
        this.totalEspb = totalEspb;
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
