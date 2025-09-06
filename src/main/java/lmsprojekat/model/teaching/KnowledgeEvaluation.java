package lmsprojekat.model.teaching;

import java.time.LocalDateTime;
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
import lmsprojekat.model.SoftDeletableEntity;
//EvaluacijaZnanja
//- vremePocetka : DateTime
//- vremeZavrsetka : DateTime
//- bodovi : int
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.LearningOutcome;

@Entity
public class KnowledgeEvaluation extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private LocalDateTime startTime;

    @Column(nullable = true)
    private LocalDateTime endTime;

    @Column(nullable = true)
    private Integer points;

    @ManyToOne(optional=true)
    @JoinColumn(name = "evaluation_instrument_id")
    private EvaluationInstrument evaluationInstrument;
    @ManyToOne(optional=true)
    @JoinColumn(name = "evaluation_type_id")
    private EvaluationType evaluationType;
    @ManyToOne(optional=true)
    @JoinColumn(name = "course_realization_id")
    private CourseRealization courseRealization;
    
    
    @ManyToMany
    @JoinTable(
      name = "knowledge_evaluation_learning_outcome", 
      joinColumns = @JoinColumn(name = "knowledge_evaluation_id"), 
      inverseJoinColumns = @JoinColumn(name = "learning_outcome_id")
    )
    private List<LearningOutcome> learningOutcomes;


	public KnowledgeEvaluation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public KnowledgeEvaluation(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer points,
			EvaluationInstrument evaluationInstrument, EvaluationType evaluationType,
			CourseRealization courseRealization, List<LearningOutcome> learningOutcomes) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.points = points;
		this.evaluationInstrument = evaluationInstrument;
		this.evaluationType = evaluationType;
		this.courseRealization = courseRealization;
		this.learningOutcomes = learningOutcomes;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getEndTime() {
		return endTime;
	}


	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}


	public Integer getPoints() {
		return points;
	}


	public void setPoints(Integer points) {
		this.points = points;
	}


	public EvaluationInstrument getEvaluationInstrument() {
		return evaluationInstrument;
	}


	public void setEvaluationInstrument(EvaluationInstrument evaluationInstrument) {
		this.evaluationInstrument = evaluationInstrument;
	}


	public EvaluationType getEvaluationType() {
		return evaluationType;
	}


	public void setEvaluationType(EvaluationType evaluationType) {
		this.evaluationType = evaluationType;
	}


	public CourseRealization getCourseRealization() {
		return courseRealization;
	}


	public void setCourseRealization(CourseRealization courseRealization) {
		this.courseRealization = courseRealization;
	}


	public List<LearningOutcome> getLearningOutcomes() {
		return learningOutcomes;
	}


	public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
		this.learningOutcomes = learningOutcomes;
	}


}
