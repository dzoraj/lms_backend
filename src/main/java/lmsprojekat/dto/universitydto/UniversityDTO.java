package lmsprojekat.dto.universitydto;

import java.time.LocalDate;
import java.util.List;

import lmsprojekat.model.Address;

public class UniversityDTO {

    private Long id;
    private String name;
    private LocalDate establishmentDate;
    private List<FacultyDTO> faculties;
    private List<Address> addresses;

    public UniversityDTO() {
    }

    public UniversityDTO(Long id, String name, LocalDate establishmentDate, List<FacultyDTO> faculties, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.establishmentDate = establishmentDate;
        this.faculties = faculties;
        this.addresses = addresses;
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

    public List<FacultyDTO> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<FacultyDTO> faculties) {
        this.faculties = faculties;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
