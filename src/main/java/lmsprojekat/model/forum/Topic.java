package lmsprojekat.model.forum;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class Topic extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; 

    @ManyToOne
    @JoinColumn(name = "user_on_forum_id", nullable = false)
    private UserOnForum author;  // Ko je napravio temu
    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic(Long id, String name, UserOnForum author, List<Post> posts, Forum forum) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.posts = posts;
		this.forum = forum;
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

	public UserOnForum getAuthor() {
		return author;
	}

	public void setAuthor(UserOnForum author) {
		this.author = author;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}
}
