package lmsprojekat.model.student;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.users.Student;
//StudentNaGodini
//- datumUpisa : date
//- brojIndeksa : String

@Entity
public class StudentInYear extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @Column(nullable = false)
    private String indexNumber; 

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; 

    @ManyToOne
    @JoinColumn(name = "study_year_id", nullable = true)
    private StudyYear studyYear;

	public StudentInYear() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentInYear(Long id, LocalDate enrollmentDate, String indexNumber, Student student, StudyYear studyYear) {
		super();
		this.id = id;
		this.enrollmentDate = enrollmentDate;
		this.indexNumber = indexNumber;
		this.student = student;
		this.studyYear = studyYear;
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

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudyYear getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(StudyYear studyYear) {
		this.studyYear = studyYear;
	} 


}
