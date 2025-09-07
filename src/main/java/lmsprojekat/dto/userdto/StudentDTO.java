package lmsprojekat.dto.userdto;

import java.util.List;

public class StudentDTO extends RegisteredUserDTO {
    private List<Long> courseAttendanceIds;
    private List<Long> studentInYearIds;
    private Long addressId;

    public StudentDTO() {}


    


    public StudentDTO(Long id, String name,String jmbg, String email, List<String> roleNames, List<Long> userOnForumIds,
		 List<Long> courseAttendanceIds, List<Long> studentInYearIds, Long addressId) {
		super(id, name, jmbg,email, roleNames, userOnForumIds);
		this.courseAttendanceIds = courseAttendanceIds;
		this.studentInYearIds = studentInYearIds;
		this.addressId = addressId;
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
