package lmsprojekat.dto.teachingdto.library;

import lmsprojekat.model.teaching.TeachingMaterial;

public class TeachingMaterialInventoryDTO {
	private Long id;
	private String name;
	private String authors;
	private Integer inventoryCount;

	public TeachingMaterialInventoryDTO() {
	}

	public TeachingMaterialInventoryDTO(Long id, String name, String authors, Integer inventoryCount) {
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.inventoryCount = inventoryCount;
	}

	public static TeachingMaterialInventoryDTO from(TeachingMaterial tm) {
		return new TeachingMaterialInventoryDTO(tm.getId(), tm.getName(), tm.getAuthors(), tm.getInventoryCount());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthors() {
		return authors;
	}

	public Integer getInventoryCount() {
		return inventoryCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setInventoryCount(Integer inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
}