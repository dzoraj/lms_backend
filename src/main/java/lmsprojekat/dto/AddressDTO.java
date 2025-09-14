package lmsprojekat.dto;

public class AddressDTO {

	private Long id;
	private String address;
	private String number;
	private String city;
	private String country;

	private Long studentId;
	private Long teacherId;
	private Long universityId;
	private Long facultyId;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String address, String number, String city, String country, Long studentId,
			Long teacherId, Long universityId, Long facultyId) {
		this.id = id;
		this.address = address;
		this.number = number;
		this.city = city;
		this.country = country;
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.universityId = universityId;
		this.facultyId = facultyId;
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

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
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
