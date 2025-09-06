package lmsprojekat.model.university;
//Fakultet
//- naziv : String
//- dekan 1

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lmsprojekat.model.Address;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.users.Teacher;

@Entity
public class Faculty extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @OneToOne(optional=true)
    @JoinColumn(name = "dean_id", nullable = false)
    private Teacher dean;
    //nije bidirekcionalno samo fakultet ima dekana, nema dekan fakultet
    
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "university_id")
    private University university;
    

    @OneToMany(mappedBy = "faculty")
    private List<Address> addresses;


	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Faculty(Long id, String name, Teacher dean, University university, List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.dean = dean;
		this.university = university;
		this.addresses = addresses;
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


	public Teacher getDean() {
		return dean;
	}


	public void setDean(Teacher dean) {
		this.dean = dean;
	}


	public University getUniversity() {
		return university;
	}


	public void setUniversity(University university) {
		this.university = university;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
