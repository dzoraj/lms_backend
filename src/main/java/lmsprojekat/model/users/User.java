package lmsprojekat.model.users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lmsprojekat.model.SoftDeletableEntity;


//Osnova za korisnike, email sifra, uloga(vrsta korisnika)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private String name;
    @Column(nullable = true, unique = true)
    private String jmbg;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",  // Join table name
        joinColumns = @JoinColumn(name = "user_id"),  // Foreign key za User
        inverseJoinColumns = @JoinColumn(name = "role_id")  // Foreign key za Role
    )
    private List<Role> roles;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String name,String jmbg, String email, String password, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.jmbg = jmbg;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}




}