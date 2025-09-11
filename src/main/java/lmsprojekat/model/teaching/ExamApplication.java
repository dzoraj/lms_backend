package lmsprojekat.model.teaching;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.student.StudentInYear;

@Entity
@Table(name = "exam_application", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "student_in_year_id", "knowledge_evaluation_id" }) }) // so students cannot apply for the same exam again
public class ExamApplication extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private LocalDateTime applicationDate = LocalDateTime.now();

	@ManyToOne(optional = false)
	@JoinColumn(name = "student_in_year_id", nullable = false)
	private StudentInYear studentInYear;

	@ManyToOne(optional = false)
	@JoinColumn(name = "knowledge_evaluation_id", nullable = false)
	private KnowledgeEvaluation knowledgeEvaluation;

	public ExamApplication() {
	}

	public ExamApplication(StudentInYear studentInYear, KnowledgeEvaluation knowledgeEvaluation) {
		this.studentInYear = studentInYear;
		this.knowledgeEvaluation = knowledgeEvaluation;
		this.applicationDate = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public StudentInYear getStudentInYear() {
		return studentInYear;
	}

	public void setStudentInYear(StudentInYear studentInYear) {
		this.studentInYear = studentInYear;
	}

	public KnowledgeEvaluation getKnowledgeEvaluation() {
		return knowledgeEvaluation;
	}

	public void setKnowledgeEvaluation(KnowledgeEvaluation knowledgeEvaluation) {
		this.knowledgeEvaluation = knowledgeEvaluation;
	}
}
