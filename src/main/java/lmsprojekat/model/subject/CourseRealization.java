package lmsprojekat.model.subject;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.Notification;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.model.teaching.TeacherOnCourse;
import lmsprojekat.model.teaching.TeachingSession;

//RealizacijaPredmeta
//note:
//Student ima konacnu
//ocenu na predmetu i
//kolekciju polaganja
//evaluacija znanja kroz
//koje skuplja bodove
@Entity
public class CourseRealization extends SoftDeletableEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@OneToMany(mappedBy = "courseRealization")
	private List<TeacherOnCourse> teachersOnCourse;

	@OneToMany(mappedBy = "courseRealization")
	private List<CourseAttendance> courseAttendances;

	@OneToMany(mappedBy = "courseRealization")
	private List<Notification> notifications;
	@OneToMany(mappedBy = "courseRealization")
	private List<KnowledgeEvaluation> knowledgeEvaluations;

	@OneToMany(mappedBy = "courseRealization")
	private List<TeachingSession> teachingSessions;

	public CourseRealization() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseRealization(Long id, Subject subject, List<TeacherOnCourse> teachersOnCourse,
			List<CourseAttendance> courseAttendances, List<Notification> notifications,
			List<KnowledgeEvaluation> knowledgeEvaluations, List<TeachingSession> teachingSessions) {
		super();
		this.id = id;
		this.subject = subject;
		this.teachersOnCourse = teachersOnCourse;
		this.courseAttendances = courseAttendances;
		this.notifications = notifications;
		this.knowledgeEvaluations = knowledgeEvaluations;
		this.teachingSessions = teachingSessions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<TeacherOnCourse> getTeachersOnCourse() {
		return teachersOnCourse;
	}

	public void setTeachersOnCourse(List<TeacherOnCourse> teachersOnCourse) {
		this.teachersOnCourse = teachersOnCourse;
	}

	public List<CourseAttendance> getCourseAttendances() {
		return courseAttendances;
	}

	public void setCourseAttendances(List<CourseAttendance> courseAttendances) {
		this.courseAttendances = courseAttendances;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<KnowledgeEvaluation> getKnowledgeEvaluations() {
		return knowledgeEvaluations;
	}

	public void setKnowledgeEvaluations(List<KnowledgeEvaluation> knowledgeEvaluations) {
		this.knowledgeEvaluations = knowledgeEvaluations;
	}

	public List<TeachingSession> getTeachingSessions() {
		return teachingSessions;
	}

	public void setTeachingSessions(List<TeachingSession> teachingSessions) {
		this.teachingSessions = teachingSessions;
	}
}
