package lmsprojekat.model.forum;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
@Entity
public class Forum extends SoftDeletableEntity{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean javni;
    
    
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Topic> topics;


	public Forum() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Forum(Long id, Boolean javni, List<Topic> topics) {
		super();
		this.id = id;
		this.javni = javni;
		this.topics = topics;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getJavni() {
		return javni;
	}


	public void setJavni(Boolean javni) {
		this.javni = javni;
	}


	public List<Topic> getTopics() {
		return topics;
	}


	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
}
