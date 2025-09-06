package lmsprojekat.dto.studentdto;
import java.time.LocalDate;

public class StudyYearDTO {
    private Long id;
    private LocalDate enrollmentDate;
    private Long studyProgramId;

    public StudyYearDTO() {}

    public StudyYearDTO(Long id, LocalDate enrollmentDate, Long studyProgramId) {
        this.id = id;
        this.enrollmentDate = enrollmentDate;
        this.studyProgramId = studyProgramId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public Long getStudyProgramId() { return studyProgramId; }
    public void setStudyProgramId(Long studyProgramId) { this.studyProgramId = studyProgramId; }
}
