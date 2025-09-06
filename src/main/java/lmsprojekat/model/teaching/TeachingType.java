package lmsprojekat.model.teaching;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//TipNastave
//- naziv : String
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;

//note:
//Predavanja
//Vezbe
//Mentorska nastava
@Entity
public class TeachingType extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;
    @OneToMany(mappedBy = "teachingType")
    private List<TeacherOnCourse> courses;
    
    @OneToMany(mappedBy = "teachingType")
    private List<TeachingSession> teachingSessions;

	public TeachingType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeachingType(Long id, String name, List<TeacherOnCourse> courses, List<TeachingSession> teachingSessions) {
		super();
		this.id = id;
		this.name = name;
		this.courses = courses;
		this.teachingSessions = teachingSessions;
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

	public List<TeacherOnCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<TeacherOnCourse> courses) {
		this.courses = courses;
	}

	public List<TeachingSession> getTeachingSessions() {
		return teachingSessions;
	}

	public void setTeachingSessions(List<TeachingSession> teachingSessions) {
		this.teachingSessions = teachingSessions;
	}


}
