package lmsprojekat.dto.subjectdto;

public class CourseAttendanceDTO {

	private Long id;
	private Integer konacnaOcena;
	private Long courseRealizationId;
	private Long studentId;

	public CourseAttendanceDTO() {
	}

	public CourseAttendanceDTO(Long id, Integer konacnaOcena, Long courseRealizationId, Long studentId) {
		this.id = id;
		this.konacnaOcena = konacnaOcena;
		this.courseRealizationId = courseRealizationId;
		this.studentId = studentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKonacnaOcena() {
		return konacnaOcena;
	}

	public void setKonacnaOcena(Integer konacnaOcena) {
		this.konacnaOcena = konacnaOcena;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
