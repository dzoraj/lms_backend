package lmsprojekat.dto.universitydto;

import java.util.List;

import lmsprojekat.dto.AddressDTO;

public class FacultyDTO {

    private Long id;
    private String name;
    private Long dean;
    private Long university;
    private List<AddressDTO> addresses;

    public FacultyDTO() {
    }

    public FacultyDTO(Long id, String name, Long dean, Long university, List<AddressDTO> addresses) {
        this.id = id;
        this.name = name;
        this.dean = dean;
        this.university = university;
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

    public Long getDean() {
        return dean;
    }

    public void setDean(Long dean) {
        this.dean = dean;
    }

    public Long getUniversity() {
        return university;
    }

    public void setUniversity(Long university) {
        this.university = university;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
