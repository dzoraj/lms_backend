package lmsprojekat.model.teaching;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.student.StudentInYear;

@Entity
public class EvaluationAttempt extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Integer points;

    @Column
    private String note;

    @ManyToOne(optional = true)
    @JoinColumn(name = "evaluation_id")
    private KnowledgeEvaluation evaluation;

    @ManyToOne(optional = true)
    @JoinColumn(name = "student_in_year_id")
    private StudentInYear studentInYear;

	public EvaluationAttempt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvaluationAttempt(Long id, Integer points, String note, KnowledgeEvaluation evaluation,
			StudentInYear studentInYear) {
		super();
		this.id = id;
		this.points = points;
		this.note = note;
		this.evaluation = evaluation;
		this.studentInYear = studentInYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public KnowledgeEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(KnowledgeEvaluation evaluation) {
		this.evaluation = evaluation;
	}

	public StudentInYear getStudentInYear() {
		return studentInYear;
	}

	public void setStudentInYear(StudentInYear studentInYear) {
		this.studentInYear = studentInYear;
	}
    
}
