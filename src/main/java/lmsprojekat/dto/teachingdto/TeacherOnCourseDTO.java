package lmsprojekat.dto.teachingdto;


import java.util.List;

import lmsprojekat.model.Notification;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.teaching.TeachingType;
import lmsprojekat.model.users.Teacher;

public class TeacherOnCourseDTO {


    private Long id;

    private Integer numberOfClasses;

    private Teacher teacher;


    private TeachingType teachingType;


    private CourseRealization courseRealization;
    

    private List<Notification> notifications;

	public TeacherOnCourseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherOnCourseDTO(Long id, Integer numberOfClasses, Teacher teacher, TeachingType teachingType,
			CourseRealization courseRealization, List<Notification> notifications) {
		super();
		this.id = id;
		this.numberOfClasses = numberOfClasses;
		this.teacher = teacher;
		this.teachingType = teachingType;
		this.courseRealization = courseRealization;
		this.notifications = notifications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumberOfClasses() {
		return numberOfClasses;
	}

	public void setNumberOfClasses(Integer numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public TeachingType getTeachingType() {
		return teachingType;
	}

	public void setTeachingType(TeachingType teachingType) {
		this.teachingType = teachingType;
	}

	public CourseRealization getCourseRealization() {
		return courseRealization;
	}

	public void setCourseRealization(CourseRealization courseRealization) {
		this.courseRealization = courseRealization;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

}
