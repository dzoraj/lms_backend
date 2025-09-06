package lmsprojekat.dto.userdto;

import java.util.List;

public class StudentDTO extends RegisteredUserDTO {
    private String name;
    private String jmbg;
    private List<Long> courseAttendanceIds;
    private List<Long> studentInYearIds;
    private Long addressId;

    public StudentDTO() {}

    public StudentDTO(Long id, String email, List<String> roleNames, List<Long> userOnForumIds,
                      String name, String jmbg, List<Long> courseAttendanceIds,
                      List<Long> studentInYearIds, Long addressId) {
        super(id, email, roleNames, userOnForumIds);
        this.name = name;
        this.jmbg = jmbg;
        this.courseAttendanceIds = courseAttendanceIds;
        this.studentInYearIds = studentInYearIds;
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public List<Long> getCourseAttendanceIds() {
        return courseAttendanceIds;
    }

    public void setCourseAttendanceIds(List<Long> courseAttendanceIds) {
        this.courseAttendanceIds = courseAttendanceIds;
    }

    public List<Long> getStudentInYearIds() {
        return studentInYearIds;
    }

    public void setStudentInYearIds(List<Long> studentInYearIds) {
        this.studentInYearIds = studentInYearIds;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
