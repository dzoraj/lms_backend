package lmsprojekat.model.teaching;
//NastavnikNaRealizaciji
//- brojCasova : Integer

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.Notification;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.users.Teacher;

@Entity
public class TeacherOnCourse extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Integer numberOfClasses;
//Mnogo realizacija moze da pripada jednom nastavniku ALI samo jedan nastavnik realizaciji?
    @ManyToOne(optional=true)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(optional=true)
    @JoinColumn(name = "teaching_type_id")
    private TeachingType teachingType;
    @ManyToOne(optional=true)
    @JoinColumn(name = "course_realization_id")
    private CourseRealization courseRealization;
    
    @OneToMany(mappedBy = "teacherOnCourse")
    private List<Notification> notifications;

	public TeacherOnCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherOnCourse(Long id, Integer numberOfClasses, Teacher teacher, TeachingType teachingType,
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
