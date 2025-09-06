package lmsprojekat.dto;

import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.university.University;
import lmsprojekat.model.users.Student;
import lmsprojekat.model.users.Teacher;
//Adresa + MESTO + DRZAVA
//- ulica : String
//- broj : String
//- naziv : String(MESTO)
//- naziv : String(DRZAVA)


public class AddressDTO {

	private Long id;

	private String AddressDTO;

	private String number;

	private String city;

	private String country;

	private Student student;

	private Teacher teacher;

	private University university;

	private Faculty faculty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressDTO() {
		return AddressDTO;
	}

	public void setAddressDTO(String addressDTO) {
		AddressDTO = addressDTO;
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

	public AddressDTO(Long id, String addressDTO, String number, String city, String country, Student student,
			Teacher teacher, University university, Faculty faculty) {
		super();
		this.id = id;
		AddressDTO = addressDTO;
		this.number = number;
		this.city = city;
		this.country = country;
		this.student = student;
		this.teacher = teacher;
		this.university = university;
		this.faculty = faculty;
	}

	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
