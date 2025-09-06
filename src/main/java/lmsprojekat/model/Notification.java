package lmsprojekat.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.teaching.TeacherOnCourse;

@Entity
public class Notification extends SoftDeletableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private LocalDateTime timePosted;

    @ManyToOne(optional=true)
    @JoinColumn(name = "course_realization_id")
    private CourseRealization courseRealization;

    @ManyToOne(optional=true)
    @JoinColumn(name = "teacher_on_course_id")
    private TeacherOnCourse teacherOnCourse;

    @OneToMany(mappedBy = "notification")
    private List<File> attachments;
    

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Notification(Long id, String title, String content, LocalDateTime timePosted,
			CourseRealization courseRealization, TeacherOnCourse teacherOnCourse, List<File> attachments) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.timePosted = timePosted;
		this.courseRealization = courseRealization;
		this.teacherOnCourse = teacherOnCourse;
		this.attachments = attachments;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(LocalDateTime timePosted) {
		this.timePosted = timePosted;
	}

	public CourseRealization getCourseRealization() {
		return courseRealization;
	}

	public void setCourseRealization(CourseRealization courseRealization) {
		this.courseRealization = courseRealization;
	}

	public TeacherOnCourse getTeacherOnCourse() {
		return teacherOnCourse;
	}

	public void setTeacherOnCourse(TeacherOnCourse teacherOnCourse) {
		this.teacherOnCourse = teacherOnCourse;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}
    
    
}

