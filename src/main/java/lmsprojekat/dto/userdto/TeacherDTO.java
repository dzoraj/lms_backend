package lmsprojekat.dto.userdto;

import java.util.List;

public class TeacherDTO extends RegisteredUserDTO {
    private String name;
    private String biography;
 
    private List<Long> titleIds;
    private List<Long> courseIds;
    private Long addressId;

    public TeacherDTO() {}

    public TeacherDTO(Long id, String name,String jmbg,String email, List<String> roleNames, List<Long> userOnForumIds,
                       String biography,  List<Long> titleIds,
                      List<Long> courseIds, Long addressId) {
        super(id,name,jmbg, email, roleNames, userOnForumIds);
        this.name=name;
        this.biography = biography;
        this.titleIds = titleIds;
        this.courseIds = courseIds;
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }



    public List<Long> getTitleIds() {
        return titleIds;
    }

    public void setTitleIds(List<Long> titleIds) {
        this.titleIds = titleIds;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
