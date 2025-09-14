package lmsprojekat.dto.universitydto;

import java.time.LocalDate;
import java.util.List;

public class UniversityDTO {
	private Long id;
	private String name;
	private LocalDate establishmentDate;
	private List<Long> facultyIds;
	private List<Long> addressIds;

	public UniversityDTO() {
	}

	public UniversityDTO(Long id, String name, LocalDate establishmentDate, List<Long> facultyIds,
			List<Long> addressIds) {
		this.id = id;
		this.name = name;
		this.establishmentDate = establishmentDate;
		this.facultyIds = facultyIds;
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

	public LocalDate getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(LocalDate establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public List<Long> getFacultyIds() {
		return facultyIds;
	}

	public void setFacultyIds(List<Long> facultyIds) {
		this.facultyIds = facultyIds;
	}

	public List<Long> getAddressIds() {
		return addressIds;
	}

	public void setAddressIds(List<Long> addressIds) {
		this.addressIds = addressIds;
	}
}
