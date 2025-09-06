package lmsprojekat.model.teaching;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.subject.LearningOutcome;

@Entity
public class EducationalGoal extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String description;

    @ManyToMany(mappedBy = "educationalGoals")
    private List<LearningOutcome> learningOutcomes;

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

	public List<LearningOutcome> getLearningOutcomes() {
		return learningOutcomes;
	}

	public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
		this.learningOutcomes = learningOutcomes;
	}

	public EducationalGoal(Long id, String description, List<LearningOutcome> learningOutcomes) {
		super();
		this.id = id;
		this.description = description;
		this.learningOutcomes = learningOutcomes;
	}

	public EducationalGoal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
