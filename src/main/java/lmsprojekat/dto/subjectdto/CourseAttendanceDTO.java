package lmsprojekat.dto.subjectdto;

public class CourseAttendanceDTO {

    private Long id;
    private Integer konacnaOcena; 
    private CourseRealizationDTO courseRealization;
    private Long studentId;  

    public CourseAttendanceDTO() {}

    public CourseAttendanceDTO(Long id, Integer konacnaOcena, CourseRealizationDTO courseRealization, Long studentId) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.courseRealization = courseRealization;
        this.studentId = studentId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getKonacnaOcena() { return konacnaOcena; }
    public void setKonacnaOcena(Integer konacnaOcena) { this.konacnaOcena = konacnaOcena; }

    public CourseRealizationDTO getCourseRealization() { return courseRealization; }
    public void setCourseRealization(CourseRealizationDTO courseRealization) { this.courseRealization = courseRealization; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

}
