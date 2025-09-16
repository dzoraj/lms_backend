package lmsprojekat.dto.teachingdto.schedule;

import java.util.ArrayList;
import java.util.List;

public class ConflictResultDTO {
	private boolean hasConflicts;
	private List<ConflictDetailDTO> details = new ArrayList<>();

	public ConflictResultDTO() {

	}

	public ConflictResultDTO(boolean hasConflicts, List<ConflictDetailDTO> details) {
		super();
		this.hasConflicts = hasConflicts;
		this.details = details;
	}

	public boolean isHasConflicts() {
		return hasConflicts;
	}

	public void setHasConflicts(boolean hasConflicts) {
		this.hasConflicts = hasConflicts;
	}

	public List<ConflictDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<ConflictDetailDTO> details) {
		this.details = details;
	}

}
