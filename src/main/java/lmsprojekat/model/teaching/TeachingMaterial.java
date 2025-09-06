package lmsprojekat.model.teaching;

import java.time.LocalDateTime;
import java.util.List;

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
import lmsprojekat.model.subject.LearningOutcome;


//NastavniMaterijal
//- naziv : String
//- autori : String[1..*] - ko ce se muciti sa ovim samo nek bude jedan string sa zarezima tipa
//- godinaIzdavanja : DateTime

@Entity
public class TeachingMaterial extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String authors; 

    @Column(nullable = true)
    private LocalDateTime yearOfPublication;


    @ManyToOne(optional=true)
    @JoinColumn(name = "learning_outcome_id")
    private LearningOutcome learningOutcome;


    @OneToMany(mappedBy = "teachingMaterial")
    private List<File> files;


	public TeachingMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TeachingMaterial(Long id, String name, String authors, LocalDateTime yearOfPublication,
			LearningOutcome learningOutcome, List<File> files) {
		super();
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.yearOfPublication = yearOfPublication;
		this.learningOutcome = learningOutcome;
		this.files = files;
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


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authors) {
		this.authors = authors;
	}


	public LocalDateTime getYearOfPublication() {
		return yearOfPublication;
	}


	public void setYearOfPublication(LocalDateTime yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}


	public LearningOutcome getLearningOutcome() {
		return learningOutcome;
	}


	public void setLearningOutcome(LearningOutcome learningOutcome) {
		this.learningOutcome = learningOutcome;
	}


	public List<File> getFiles() {
		return files;
	}


	public void setFiles(List<File> files) {
		this.files = files;
	}
}
