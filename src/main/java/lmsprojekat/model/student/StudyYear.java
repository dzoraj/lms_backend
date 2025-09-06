package lmsprojekat.model.student;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.subject.Subject;
//GodinaStudija
//- godina : DateTime[2]

@Entity
public class StudyYear extends SoftDeletableEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate enrollmentDate;
    
	@ManyToOne
	@JoinColumn(name = "study_program_id", nullable = true)
	private StudyProgram studyProgram;

	@OneToMany(mappedBy = "studyYear")
	private List<Subject> subjects;

	public StudyYear() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyYear(Long id, LocalDate enrollmentDate, StudyProgram studyProgram, List<Subject> subjects) {
		super();
		this.id = id;
		this.enrollmentDate = enrollmentDate;
		this.studyProgram = studyProgram;
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public StudyProgram getStudyProgram() {
		return studyProgram;
	}

	public void setStudyProgram(StudyProgram studyProgram) {
		this.studyProgram = studyProgram;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
