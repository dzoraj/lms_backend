package lmsprojekat.dto.universitydto;

import java.util.List;

import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.model.Address;

public class FacultyDTO {

    private Long id;
    private String name;
    private TeacherDTO dean;
    private UniversityDTO university;
    private List<Address> addresses;

    public FacultyDTO() {
    }

    public FacultyDTO(Long id, String name, TeacherDTO dean, UniversityDTO university, List<Address> addresses) {
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

    public TeacherDTO getDean() {
        return dean;
    }

    public void setDean(TeacherDTO dean) {
        this.dean = dean;
    }

    public UniversityDTO getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDTO university) {
        this.university = university;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
