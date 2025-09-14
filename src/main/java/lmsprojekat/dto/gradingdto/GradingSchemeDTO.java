package lmsprojekat.dto.gradingdto;

import java.util.List;

public class GradingSchemeDTO{


	private Long id;

	private Integer totalPoints; 
	
	private Integer threshold;
	

	private List<GradeBoundaryDTO> gradeBoundaries;

	public GradingSchemeDTO() {
		super();
	}

	public GradingSchemeDTO(Long id, Integer totalPoints,Integer threshold , List<GradeBoundaryDTO> gradeBoundaries) {
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

	public List<GradeBoundaryDTO> getGradeBoundaries() {
		return gradeBoundaries;
	}

	public void setGradeBoundaries(List<GradeBoundaryDTO> gradeBoundaries) {
		this.gradeBoundaries = gradeBoundaries;
	}
}
