package lmsprojekat.dto.titledto;

public class ScientificFieldDTO {
	private Long id;
	private String name;
	private Long titleId; 

	public ScientificFieldDTO() {
	}

	public ScientificFieldDTO(Long id, String name, Long titleId) {
		this.id = id;
		this.name = name;
		this.titleId = titleId;
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

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
}
