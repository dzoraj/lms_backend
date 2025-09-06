package lmsprojekat.model.university;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.Address;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class University extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;
    
    @Column(nullable = true)  
    private LocalDate establishmentDate;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Faculty> faculties;
    

    @OneToMany(mappedBy = "university")
    private List<Address> addresses;


	public University() {
		super();
		// TODO Auto-generated constructor stub
	}


	public University(Long id, String name, LocalDate establishmentDate, List<Faculty> faculties,
			List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.establishmentDate = establishmentDate;
		this.faculties = faculties;
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


	public LocalDate getEstablishmentDate() {
		return establishmentDate;
	}


	public void setEstablishmentDate(LocalDate establishmentDate) {
		this.establishmentDate = establishmentDate;
	}


	public List<Faculty> getFaculties() {
		return faculties;
	}


	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
