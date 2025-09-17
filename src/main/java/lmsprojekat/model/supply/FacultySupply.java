package lmsprojekat.model.supply;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lmsprojekat.model.university.Faculty;

@Entity

public class FacultySupply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(nullable = false)
	private Integer quantity;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@PrePersist
	@PreUpdate
	void touch() {
		this.updatedAt = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
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
}
