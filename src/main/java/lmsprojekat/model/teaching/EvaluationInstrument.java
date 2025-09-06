package lmsprojekat.model.teaching;

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
// Sta se podrazumeva pod
//instrumentom evaluacije
// PO MENI SAMO DODATI NAZIV kolonu za sad jer instrument evaluacije moze biti mtutor platforma, obican pdf...
@Entity
public class EvaluationInstrument extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private String name;

    @OneToMany(mappedBy = "evaluationInstrument")
    private List<KnowledgeEvaluation> evaluations;

    @ManyToOne(optional = true)
    @JoinColumn(name = "file_id")
    private File file;

	public EvaluationInstrument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvaluationInstrument(Long id, String name, List<KnowledgeEvaluation> evaluations, File file) {
		super();
		this.id = id;
		this.name = name;
		this.evaluations = evaluations;
		this.file = file;
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

	public List<KnowledgeEvaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<KnowledgeEvaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
