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
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.LearningOutcome;


//TerminNastave
//- vremePocetka : DateTime
//- vremeKraja : DateTime

@Entity
public class TeachingSession extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private LocalDateTime startTime;

    @Column(nullable = true)
    private LocalDateTime endTime;

    @ManyToOne(optional=true)
    @JoinColumn(name = "course_realization_id")
    private CourseRealization courseRealization;
    
    @ManyToOne(optional=true)
    @JoinColumn(name = "teaching_type_id")
    private TeachingType teachingType;

    @ManyToMany
    @JoinTable(
      name = "teaching_session_learning_outcome", 
      joinColumns = @JoinColumn(name = "teaching_session_id"), 
      inverseJoinColumns = @JoinColumn(name = "learning_outcome_id")
    )
    private List<LearningOutcome> learningOutcomes;

	public TeachingSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeachingSession(Long id, LocalDateTime startTime, LocalDateTime endTime, CourseRealization courseRealization,
			TeachingType teachingType, List<LearningOutcome> learningOutcomes) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.courseRealization = courseRealization;
		this.teachingType = teachingType;
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

	public CourseRealization getCourseRealization() {
		return courseRealization;
	}

	public void setCourseRealization(CourseRealization courseRealization) {
		this.courseRealization = courseRealization;
	}

	public TeachingType getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(TeachingType teachingType) {
		this.teachingType = teachingType;
	}

	public List<LearningOutcome> getLearningOutcomes() {
		return learningOutcomes;
	}

	public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
		this.learningOutcomes = learningOutcomes;
	}

}
