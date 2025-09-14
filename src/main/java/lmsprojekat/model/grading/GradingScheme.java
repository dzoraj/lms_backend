package lmsprojekat.model.grading;

import java.util.List;

import jakarta.persistence.*;
import lmsprojekat.model.SoftDeletableEntity;

@Entity
public class GradingScheme extends SoftDeletableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer totalPoints; // max points for the subject

	@Column(nullable = false)
	private Integer threshold; // minimum points required to pass

	@OneToMany(mappedBy = "gradingScheme")
	private List<GradeBoundary> gradeBoundaries;

	public GradingScheme() {
		super();
	}

	public GradingScheme(Long id, Integer totalPoints, Integer threshold, List<GradeBoundary> gradeBoundaries) {
		super();
		this.id = id;
		this.totalPoints = totalPoints;
		this.threshold = threshold;
		this.gradeBoundaries = gradeBoundaries;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public List<GradeBoundary> getGradeBoundaries() {
		return gradeBoundaries;
	}

	public void setGradeBoundaries(List<GradeBoundary> gradeBoundaries) {
		this.gradeBoundaries = gradeBoundaries;
	}
}
