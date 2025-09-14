package lmsprojekat.dto.teachingdto;

import java.util.List;

public class EvaluationInstrumentDTO {
	private Long id;
	private String name;
	private List<Long> evaluationIds;
	private Long fileId;

	public EvaluationInstrumentDTO() {
	}

	public EvaluationInstrumentDTO(Long id, String name, List<Long> evaluationIds, Long fileId) {
		this.id = id;
		this.name = name;
		this.evaluationIds = evaluationIds;
		this.fileId = fileId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getEvaluationIds() {
		return evaluationIds;
	}

	public void setEvaluationIds(List<Long> evaluationIds) {
		this.evaluationIds = evaluationIds;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
}
