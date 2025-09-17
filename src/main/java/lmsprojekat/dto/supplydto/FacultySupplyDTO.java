package lmsprojekat.dto.supplydto;

import java.time.LocalDateTime;

public class FacultySupplyDTO {
	public Long id;
	public Long facultyId;
	public String itemName;
	public Integer quantity;
	public LocalDateTime updatedAt;

	public FacultySupplyDTO(Long id, Long facultyId, String itemName, Integer quantity, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.facultyId = facultyId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.updatedAt = updatedAt;
	}

	public FacultySupplyDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
