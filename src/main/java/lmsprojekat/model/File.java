package lmsprojekat.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.forum.Post;
import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.model.teaching.TeachingMaterial;

@Entity
public class File extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String url;

    @ManyToOne(optional=true)
    @JoinColumn(name = "post_id")
    private Post post;
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "notification_id")
    private Notification notification;
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "message_id")
    private Message message; 
    
    @OneToMany(mappedBy = "file")
    private List<EvaluationInstrument> evaluationInstruments;
//SVAKI DO SAD JE BIO MANY TO ONE ZATO STO SAMO JEDAN FILE PO POSTU... A ovde je obrnuto
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "teaching_material_id")
    private TeachingMaterial teachingMaterial;

	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public File(Long id, String description, String url, Post post, Notification notification, Message message,
		List<EvaluationInstrument> evaluationInstruments, TeachingMaterial teachingMaterial) {
	super();
	this.id = id;
	this.description = description;
	this.url = url;
	this.post = post;
	this.notification = notification;
	this.message = message;
	this.evaluationInstruments = evaluationInstruments;
	this.teachingMaterial = teachingMaterial;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<EvaluationInstrument> getEvaluationInstruments() {
		return evaluationInstruments;
	}

	public void setEvaluationInstruments(List<EvaluationInstrument> evaluationInstruments) {
		this.evaluationInstruments = evaluationInstruments;
	}

	public TeachingMaterial getTeachingMaterial() {
		return teachingMaterial;
	}

	public void setTeachingMaterial(TeachingMaterial teachingMaterial) {
		this.teachingMaterial = teachingMaterial;
	}

}