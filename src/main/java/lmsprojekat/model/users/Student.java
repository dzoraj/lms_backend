package lmsprojekat.model.users;

//<<actor>>
//Student
//- jmbg : String
//- ime : String

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lmsprojekat.model.Address;
import lmsprojekat.model.forum.UserOnForum;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.subject.CourseAttendance;

@Entity
public class Student extends RegisteredUser {


    @OneToMany(mappedBy = "student")
    private List<CourseAttendance> courseAttendances;
    @OneToMany(mappedBy = "student")
    private List<StudentInYear> studentInYear;
    @OneToOne(optional=true)
    @JoinColumn(name = "address_id")
    private Address address;
    
    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id,String name,String jmbg, String email, String password, List<Role> roles, List<UserOnForum> userOnForums, List<CourseAttendance> courseAttendances, List<StudentInYear> studentInYear,
			Address address) {
		super(id,name,jmbg, email, password, roles, userOnForums);
		this.courseAttendances = courseAttendances;
		this.studentInYear = studentInYear;
		this.address = address;
	}
	

	public List<CourseAttendance> getCourseAttendances() {
		return courseAttendances;
	}
	public void setCourseAttendances(List<CourseAttendance> courseAttendances) {
		this.courseAttendances = courseAttendances;
	}
	public List<StudentInYear> getStudentInYear() {
		return studentInYear;
	}
	public void setStudentInYear(List<StudentInYear> studentInYear) {
		this.studentInYear = studentInYear;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}