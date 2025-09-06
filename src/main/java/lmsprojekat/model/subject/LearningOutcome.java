package lmsprojekat.model.subject;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.teaching.EducationalGoal;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
//Ishod
//- opis : String
//- silabus
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.model.teaching.TeachingSession;

//Silabus je skup ishoda,
//odnosno tema koje se
//rade na predmetu po
//terminima

//Postoje i aktivnosti koje
//dovode do ostvarivanja
//ishoda
//Vise ishoda moze da
//vodi istom obrazovnom
//cilju, a i jedan ishod
//moze da ucestvuje u
//realizaciji vise
//obrazovnih ciljeva u
//modelu

//-- VISE ISHODA ZNACI MOZE ISTOM OBRAZOVNOM CILJU, A JEDAN ISHOD U VISE  CILJEVA ZNACI MANY TO MANY
@Entity
public class LearningOutcome extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String description; 
    
    @ManyToOne
    @JoinColumn(name = "subject_id",nullable = true)
    private Subject subject;
    
    @ManyToMany
    @JoinTable(
        name = "learning_outcome_educational_goal", 
        joinColumns = @JoinColumn(name = "learning_outcome_id"), 
        inverseJoinColumns = @JoinColumn(name = "educational_goal_id")
    )
    private List<EducationalGoal> educationalGoals;
    
    @OneToMany(mappedBy = "learningOutcome")
    private List<TeachingMaterial> teachingMaterials;
    
    @ManyToMany(mappedBy = "learningOutcomes")
    private List<KnowledgeEvaluation> knowledgeEvaluations;
    
    @ManyToMany(mappedBy = "learningOutcomes")
    private List<TeachingSession> teachingSessions;

	public LearningOutcome() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LearningOutcome(Long id, String description, Subject subject, List<EducationalGoal> educationalGoals,
			List<TeachingMaterial> teachingMaterials, List<KnowledgeEvaluation> knowledgeEvaluations,
			List<TeachingSession> teachingSessions) {
		super();
		this.id = id;
		this.description = description;
		this.subject = subject;
		this.educationalGoals = educationalGoals;
		this.teachingMaterials = teachingMaterials;
		this.knowledgeEvaluations = knowledgeEvaluations;
		this.teachingSessions = teachingSessions;
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<EducationalGoal> getEducationalGoals() {
		return educationalGoals;
	}

	public void setEducationalGoals(List<EducationalGoal> educationalGoals) {
		this.educationalGoals = educationalGoals;
	}

	public List<TeachingMaterial> getTeachingMaterials() {
		return teachingMaterials;
	}

	public void setTeachingMaterials(List<TeachingMaterial> teachingMaterials) {
		this.teachingMaterials = teachingMaterials;
	}

	public List<KnowledgeEvaluation> getKnowledgeEvaluations() {
		return knowledgeEvaluations;
	}

	public void setKnowledgeEvaluations(List<KnowledgeEvaluation> knowledgeEvaluations) {
		this.knowledgeEvaluations = knowledgeEvaluations;
	}

	public List<TeachingSession> getTeachingSessions() {
		return teachingSessions;
	}

	public void setTeachingSessions(List<TeachingSession> teachingSessions) {
		this.teachingSessions = teachingSessions;
	}
}
