package lmsprojekat.dto.teachingdto;

import java.util.List;

public class EvaluationTypeDTO {
	private Long id;
	private String name;
	private List<Long> knowledgeEvaluationIds; 

	public EvaluationTypeDTO() {
	}

	public EvaluationTypeDTO(Long id, String name, List<Long> knowledgeEvaluationIds) {
		this.id = id;
		this.name = name;
		this.knowledgeEvaluationIds = knowledgeEvaluationIds;
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

	public List<Long> getKnowledgeEvaluationIds() {
		return knowledgeEvaluationIds;
	}

	public void setKnowledgeEvaluationIds(List<Long> knowledgeEvaluationIds) {
		this.knowledgeEvaluationIds = knowledgeEvaluationIds;
	}
}
