package lmsprojekat.dto.teachingdto.library;

import java.time.LocalDateTime;
import lmsprojekat.model.teaching.MaterialLoan;

public class LoanDTO {
	private Long id;
	private Long teachingMaterialId;
	private String teachingMaterialName;
	private Long studentId;
	private Long studentInYearId;
	private String studentName;
	private String indexNumber;
	private Integer quantity;
	private LocalDateTime issuedAt;
	private LocalDateTime returnedAt;

	public LoanDTO() {
	}

	public LoanDTO(Long id, Long teachingMaterialId, String teachingMaterialName, Long studentId, Long studentInYearId,
			String studentName, String indexNumber, Integer quantity, LocalDateTime issuedAt,
			LocalDateTime returnedAt) {
		this.id = id;
		this.teachingMaterialId = teachingMaterialId;
		this.teachingMaterialName = teachingMaterialName;
		this.studentId = studentId;
		this.studentInYearId = studentInYearId;
		this.studentName = studentName;
		this.indexNumber = indexNumber;
		this.quantity = quantity;
		this.issuedAt = issuedAt;
		this.returnedAt = returnedAt;
	}

	public static LoanDTO from(MaterialLoan m) {
		var siy = m.getStudentInYear();
		var student = siy != null ? siy.getStudent() : null;
		Long sId = student != null ? student.getId() : null;
		Long siyId = siy != null ? siy.getId() : null;
		String sName = student != null ? student.getName() : null;
		String idx = siy != null ? siy.getIndexNumber() : null;
		return new LoanDTO(m.getId(), m.getTeachingMaterial().getId(), m.getTeachingMaterial().getName(), sId, siyId,
				sName, idx, m.getQuantity(), m.getIssuedAt(), m.getReturnedAt());
	}

	public Long getId() {
		return id;
	}

	public Long getTeachingMaterialId() {
		return teachingMaterialId;
	}

	public String getTeachingMaterialName() {
		return teachingMaterialName;
	}

	public Long getStudentId() {
		return studentId;
	}

	public Long getStudentInYearId() {
		return studentInYearId;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public LocalDateTime getIssuedAt() {
		return issuedAt;
	}

	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTeachingMaterialId(Long teachingMaterialId) {
		this.teachingMaterialId = teachingMaterialId;
	}

	public void setTeachingMaterialName(String teachingMaterialName) {
		this.teachingMaterialName = teachingMaterialName;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setStudentInYearId(Long studentInYearId) {
		this.studentInYearId = studentInYearId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setIssuedAt(LocalDateTime issuedAt) {
		this.issuedAt = issuedAt;
	}

	public void setReturnedAt(LocalDateTime returnedAt) {
		this.returnedAt = returnedAt;
	}
}
