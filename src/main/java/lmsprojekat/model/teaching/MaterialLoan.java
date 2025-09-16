package lmsprojekat.model.teaching;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.student.StudentInYear;

@Entity
@Table(name = "material_loan")
public class MaterialLoan extends SoftDeletableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "teaching_material_id")
	private TeachingMaterial teachingMaterial;

	@ManyToOne(optional = false)
	@JoinColumn(name = "student_in_year_id")
	private StudentInYear studentInYear;

	@Column(nullable = false)
	private Integer quantity = 1;

	@Column(nullable = false)
	private LocalDateTime issuedAt;

	private LocalDateTime returnedAt;

	public Long getId() {
		return id;
	}

	public TeachingMaterial getTeachingMaterial() {
		return teachingMaterial;
	}

	public void setTeachingMaterial(TeachingMaterial teachingMaterial) {
		this.teachingMaterial = teachingMaterial;
	}

	public StudentInYear getStudentInYear() {
		return studentInYear;
	}

	public void setStudentInYear(StudentInYear studentInYear) {
		this.studentInYear = studentInYear;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}

	public void setReturnedAt(LocalDateTime returnedAt) {
		this.returnedAt = returnedAt;
	}
}
