package lmsprojekat.model.users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lmsprojekat.model.Address;
import lmsprojekat.model.forum.UserOnForum;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.model.title.Title;

//<<actor>>
//Nastavnik
//
//- biografija : String
//- ime : String
//- jmbg : String


@Entity
public class Teacher extends RegisteredUser {

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String biography;

    @Column(nullable = true, unique = true)
    private String jmbg;

    @OneToMany(mappedBy = "teacher")
    private List<Title> titles;
    @OneToMany(mappedBy = "teacher")
    private List<TeacherOnCourse> courses;
    
    @OneToOne(optional=true)
    @JoinColumn(name = "address_id")
    private Address address;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Teacher(Long id, String email, String password, List<Role> roles, List<UserOnForum> userOnForums,
			String name, String biography, String jmbg, List<Title> titles, List<TeacherOnCourse> courses,
			Address address) {
		super(id, email, password, roles, userOnForums);
		this.name = name;
		this.biography = biography;
		this.jmbg = jmbg;
		this.titles = titles;
		this.courses = courses;
		this.address = address;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<TeacherOnCourse> getCourses() {
		return courses;
	}

	public void setCourses(List<TeacherOnCourse> courses) {
		this.courses = courses;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

}
