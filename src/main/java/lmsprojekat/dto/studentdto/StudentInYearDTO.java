package lmsprojekat.dto.studentdto;
import java.time.LocalDate;

public class StudentInYearDTO {
    private Long id;
    private LocalDate enrollmentDate;
    private String indexNumber;
    private Long studentId;
    private Long studyYearId;

    public StudentInYearDTO() {}

    public StudentInYearDTO(Long id, LocalDate enrollmentDate, String indexNumber, Long studentId, Long studyYearId) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.indexNumber = indexNumber;
        this.studentId = studentId;
        this.studyYearId = studyYearId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getIndexNumber() { return indexNumber; }
    public void setIndexNumber(String indexNumber) { this.indexNumber = indexNumber; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getStudyYearId() { return studyYearId; }
    public void setStudyYearId(Long studyYearId) { this.studyYearId = studyYearId; }
}
