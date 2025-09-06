package lmsprojekat.model.forum;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.model.users.Role;
@Entity
public class UserOnForum extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role; 

    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;  

    @ManyToOne
    @JoinColumn(name = "registered_user_id", nullable = false)
    private RegisteredUser registeredUser;  // Ulogovani korisnik
    
    // Agregacija prema temi i objavi
    @OneToMany(mappedBy = "author")
    private List<Topic> topics;  //Korisnikove teme

    @OneToMany(mappedBy = "author")
    private List<Post> posts;  //Korisnikove objave

	public UserOnForum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserOnForum(Long id, Role role, Forum forum, RegisteredUser registeredUser, List<Topic> topics,
			List<Post> posts) {
		super();
		this.id = id;
		this.role = role;
		this.forum = forum;
		this.registeredUser = registeredUser;
		this.topics = topics;
		this.posts = posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}