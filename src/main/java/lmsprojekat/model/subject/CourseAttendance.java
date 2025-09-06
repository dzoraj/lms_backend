package lmsprojekat.model.subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.users.Student;


//PohadjanjePredmeta
//- konacnaOcena : int

@Entity
public class CourseAttendance extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private Integer konacnaOcena; 

    @ManyToOne(optional = true)
    @JoinColumn(name = "course_realization_id")
    private CourseRealization courseRealization;

    @ManyToOne(optional = true)
    @JoinColumn(name = "student_id")
    private Student student;
    //Prisustvo na kursu za studente: Posto student moze da pohadja vise realizacija kursa, to je odnos ManyToOne.
    //Svaki zapis o pohadjanju kursa (PohadjanjePredmeta) bice povezan sa jednim studentom.

	public Long getId() {
		return id;
	}

	public CourseAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseAttendance(Long id, Integer konacnaOcena, CourseRealization courseRealization, Student student) {
		super();
		this.id = id;
		this.konacnaOcena = konacnaOcena;
		this.courseRealization = courseRealization;
		this.student = student;
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

	public CourseRealization getCourseRealization() {
		return courseRealization;
	}

	public void setCourseRealization(CourseRealization courseRealization) {
		this.courseRealization = courseRealization;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
    


}
