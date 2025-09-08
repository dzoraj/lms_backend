package lmsprojekat.dto;

import lmsprojekat.model.users.Student;
import lmsprojekat.model.users.Teacher;

public class AddressDTO {

	private Long id;
	private String address; 
	private String number;
	private String city;
	private String country;

	private Student student;
	private Teacher teacher;

	private Long universityId; 
	private Long facultyId;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String address, String number, String city, String country, Student student,
			Teacher teacher, Long universityId, Long facultyId) {
		this.id = id;
		this.address = address;
		this.number = number;
		this.city = city;
		this.country = country;
		this.student = student;
		this.teacher = teacher;
		this.universityId = universityId;
		this.facultyId = facultyId;
	}

	// getters and setters
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

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
}
