package lmsprojekat.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.users.RegisteredUser;

@Entity
public class Message extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateSent; 

    @Column(nullable = false)
    private String content; 

    @ManyToOne(optional=true)
    @JoinColumn(name = "sender_id", nullable = false)
    private RegisteredUser sender; 

    @ManyToOne(optional=true)
    @JoinColumn(name = "receiver_id", nullable = false)
    private RegisteredUser receiver;

    @OneToMany(mappedBy = "message")
    private List<File> attachments;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Long id, Date dateSent, String content, RegisteredUser sender, RegisteredUser receiver,
			List<File> attachments) {
		super();
		this.id = id;
		this.dateSent = dateSent;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.attachments = attachments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RegisteredUser getSender() {
		return sender;
	}

	public void setSender(RegisteredUser sender) {
		this.sender = sender;
	}

	public RegisteredUser getReceiver() {
		return receiver;
	}

	public void setReceiver(RegisteredUser receiver) {
		this.receiver = receiver;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}
    
}
