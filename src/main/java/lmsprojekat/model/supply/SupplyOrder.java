package lmsprojekat.model.supply;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lmsprojekat.model.university.Faculty;

@Entity
public class SupplyOrder {
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

	@Column(nullable = false)
	private String status = "PLACED";

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
