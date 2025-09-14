package lmsprojekat.model.grading;

import jakarta.persistence.*;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class GradeBoundary extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer minPoints;

	@Column(nullable = false)
	private Integer gradeValue;

	@ManyToOne
	@JoinColumn(name = "grading_scheme_id", nullable = false)
	private GradingScheme gradingScheme;

	public GradeBoundary() {
		super();
	}

	public GradeBoundary(Long id, Integer minPoints, Integer gradeValue, GradingScheme gradingScheme) {
		super();
		this.id = id;
		this.minPoints = minPoints;
		this.gradeValue = gradeValue;
		this.gradingScheme = gradingScheme;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(Integer minPoints) {
		this.minPoints = minPoints;
	}

	public Integer getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(Integer gradeValue) {
		this.gradeValue = gradeValue;
	}

	public GradingScheme getGradingScheme() {
		return gradingScheme;
	}

	public void setGradingScheme(GradingScheme gradingScheme) {
		this.gradingScheme = gradingScheme;
	}
}
