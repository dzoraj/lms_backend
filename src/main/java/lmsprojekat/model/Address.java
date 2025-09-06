package lmsprojekat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.model.users.Student;
import lmsprojekat.model.users.Teacher;
//Adresa + MESTO + DRZAVA
//- ulica : String
//- broj : String
//- naziv : String(MESTO)
//- naziv : String(DRZAVA)

@Entity
public class Address extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String address; 

    @Column(nullable = true)
    private String number; 

    @Column(nullable = true)
    private String city; 

    @Column(nullable = true)
    private String country; 

    @OneToOne(mappedBy = "address",optional=true)
    private Student student; 
    
    @OneToOne(mappedBy = "address",optional=true)
    private Teacher teacher; 
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne(optional=true)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(Long id, String address, String number, String city, String country, Student student,
			Teacher teacher, University university, Faculty faculty) {
		super();
		this.id = id;
		this.address = address;
		this.number = number;
		this.city = city;
		this.country = country;
		this.student = student;
		this.teacher = teacher;
		this.university = university;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
