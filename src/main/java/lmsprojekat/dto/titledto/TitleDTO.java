package lmsprojekat.dto.titledto;

import java.time.LocalDate;
import java.util.List;

public class TitleDTO {
	private Long id;
	private LocalDate selectionDate;
	private LocalDate endDate;
	private Long teacherId;
	private List<Long> scientificFieldIds;
	private List<Long> titleTypeIds;

	public TitleDTO() {
	}

	public TitleDTO(Long id, LocalDate selectionDate, LocalDate endDate, Long teacherId, List<Long> scientificFieldIds,
			List<Long> titleTypeIds) {
		this.id = id;
		this.selectionDate = selectionDate;
		this.endDate = endDate;
		this.teacherId = teacherId;
		this.scientificFieldIds = scientificFieldIds;
		this.titleTypeIds = titleTypeIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getSelectionDate() {
		return selectionDate;
	}

	public void setSelectionDate(LocalDate selectionDate) {
		this.selectionDate = selectionDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public List<Long> getScientificFieldIds() {
		return scientificFieldIds;
	}

	public void setScientificFieldIds(List<Long> scientificFieldIds) {
		this.scientificFieldIds = scientificFieldIds;
	}

	public List<Long> getTitleTypeIds() {
		return titleTypeIds;
	}

	public void setTitleTypeIds(List<Long> titleTypeIds) {
		this.titleTypeIds = titleTypeIds;
	}
}
