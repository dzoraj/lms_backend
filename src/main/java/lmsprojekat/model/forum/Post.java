package lmsprojekat.model.forum;

import java.time.LocalDate;
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
import lmsprojekat.model.File;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class Post extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private LocalDate postingTime;
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_on_forum_id", nullable = false)
    private UserOnForum author;// ko je napravio post
    
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<File> attachments;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Long id, LocalDate postingTime, String content, UserOnForum author, Topic topic,
			List<File> attachments) {
		super();
		this.id = id;
		this.postingTime = postingTime;
		this.content = content;
		this.author = author;
		this.topic = topic;
		this.attachments = attachments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(LocalDate postingTime) {
		this.postingTime = postingTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserOnForum getAuthor() {
		return author;
	}

	public void setAuthor(UserOnForum author) {
		this.author = author;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

}
