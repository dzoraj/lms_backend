package lmsprojekat.dto.universitydto;

import java.util.List;

public class FacultyDTO {
	private Long id;
	private String name;
	private Long deanId;
	private Long universityId;
	private List<Long> addressIds;

	public FacultyDTO() {
	}

	public FacultyDTO(Long id, String name, Long deanId, Long universityId, List<Long> addressIds) {
		this.id = id;
		this.name = name;
		this.deanId = deanId;
		this.universityId = universityId;
		this.addressIds = addressIds;
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

	public Long getDeanId() {
		return deanId;
	}

	public void setDeanId(Long deanId) {
		this.deanId = deanId;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public List<Long> getAddressIds() {
		return addressIds;
	}

	public void setAddressIds(List<Long> addressIds) {
		this.addressIds = addressIds;
	}
}
