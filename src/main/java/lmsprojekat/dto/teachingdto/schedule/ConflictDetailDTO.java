package lmsprojekat.dto.teachingdto.schedule;

public class ConflictDetailDTO {
	private String kind;
	private ScheduleItemType againstType;
	private Long againstId;
	private String info;

	public ConflictDetailDTO() {
	}

	public ConflictDetailDTO(String kind, ScheduleItemType againstType, Long againstId, String info) {
		super();
		this.kind = kind;
		this.againstType = againstType;
		this.againstId = againstId;
		this.info = info;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public ScheduleItemType getAgainstType() {
		return againstType;
	}

	public void setAgainstType(ScheduleItemType againstType) {
		this.againstType = againstType;
	}

	public Long getAgainstId() {
		return againstId;
	}

	public void setAgainstId(Long againstId) {
		this.againstId = againstId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}