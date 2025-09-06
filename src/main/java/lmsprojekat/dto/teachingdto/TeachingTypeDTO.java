package lmsprojekat.dto.teachingdto;

import java.util.List;

public class TeachingTypeDTO {

    private Long id;
    private String name;

    private List<Long> teacherOnCourseIds;
    private List<Long> teachingSessionIds;

    public TeachingTypeDTO() {}

    public TeachingTypeDTO(Long id, String name, List<Long> teacherOnCourseIds, List<Long> teachingSessionIds) {
        this.id = id;
        this.name = name;
        this.teacherOnCourseIds = teacherOnCourseIds;
        this.teachingSessionIds = teachingSessionIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Long> getTeacherOnCourseIds() { return teacherOnCourseIds; }
    public void setTeacherOnCourseIds(List<Long> teacherOnCourseIds) { this.teacherOnCourseIds = teacherOnCourseIds; }

    public List<Long> getTeachingSessionIds() { return teachingSessionIds; }
    public void setTeachingSessionIds(List<Long> teachingSessionIds) { this.teachingSessionIds = teachingSessionIds; }
}
