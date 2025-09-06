package lmsprojekat.model.teaching;

//Kolokvijum
//Test
//Projekat
//Usmeni ispit
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
@Entity
public class EvaluationType extends SoftDeletableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private String name;

    @OneToMany(mappedBy = "evaluationType")
    private List<KnowledgeEvaluation> evaluations;

	public EvaluationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvaluationType(Long id, String name, List<KnowledgeEvaluation> evaluations) {
		super();
		this.id = id;
		this.name = name;
		this.evaluations = evaluations;
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

}
