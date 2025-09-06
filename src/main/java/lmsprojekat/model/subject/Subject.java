package lmsprojekat.model.subject;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.student.StudyYear;

//Predmet
//- naziv: String
//- espb : Integer
//- obavezan : boolean
//- brojPredavanja : Integer
//- brojVezbi : Integer
//- drugiObliciNastave : Integer
//- istrazivackiRad : Integer
//- ostaliCasovi : Integer

@Entity
public class Subject extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private Integer espb;

    @Column(nullable = true)
    private Boolean mandatory;

    @Column(nullable = true)
    private Integer lectureCount;

    @Column(nullable = true)
    private Integer labCount;

    @Column(nullable = true)
    private Integer otherTeachingForms;

    @Column(nullable = true)
    private Integer researchWork;

    @Column(nullable = true)
    private Integer otherClasses;
    
    @ManyToOne
    @JoinColumn(name = "study_year_id")
    private StudyYear studyYear;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "subject_id")
    private List<LearningOutcome> syllabus; 
    //silabus je skup ishoda sto znaci da sam dobro uradio
    //jer je ovo List ishoda 

    
    //AGREGACIJA PREDMETA SAMOG U SEBE
    @OneToMany
    @JoinColumn(name = "parent_subject_id")
    private List<Subject> subSubjects;

    @ManyToOne
    @JoinColumn(name = "parent_subject_id", insertable = false, updatable = false)
    private Subject parentSubject;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(Long id, String name, Integer espb, Boolean mandatory, Integer lectureCount, Integer labCount,
			Integer otherTeachingForms, Integer researchWork, Integer otherClasses, StudyYear studyYear,
			List<LearningOutcome> syllabus, List<Subject> subSubjects, Subject parentSubject) {
		super();
		this.id = id;
		this.name = name;
		this.espb = espb;
		this.mandatory = mandatory;
		this.lectureCount = lectureCount;
		this.labCount = labCount;
		this.otherTeachingForms = otherTeachingForms;
		this.researchWork = researchWork;
		this.otherClasses = otherClasses;
		this.studyYear = studyYear;
		this.syllabus = syllabus;
		this.subSubjects = subSubjects;
		this.parentSubject = parentSubject;
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

	public Integer getEspb() {
		return espb;
	}

	public void setEspb(Integer espb) {
		this.espb = espb;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Integer getLectureCount() {
		return lectureCount;
	}

	public void setLectureCount(Integer lectureCount) {
		this.lectureCount = lectureCount;
	}

	public Integer getLabCount() {
		return labCount;
	}

	public void setLabCount(Integer labCount) {
		this.labCount = labCount;
	}

	public Integer getOtherTeachingForms() {
		return otherTeachingForms;
	}

	public void setOtherTeachingForms(Integer otherTeachingForms) {
		this.otherTeachingForms = otherTeachingForms;
	}

	public Integer getResearchWork() {
		return researchWork;
	}

	public void setResearchWork(Integer researchWork) {
		this.researchWork = researchWork;
	}

	public Integer getOtherClasses() {
		return otherClasses;
	}

	public void setOtherClasses(Integer otherClasses) {
		this.otherClasses = otherClasses;
	}

	public StudyYear getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(StudyYear studyYear) {
		this.studyYear = studyYear;
	}

	public List<LearningOutcome> getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(List<LearningOutcome> syllabus) {
		this.syllabus = syllabus;
	}

	public List<Subject> getSubSubjects() {
		return subSubjects;
	}

	public void setSubSubjects(List<Subject> subSubjects) {
		this.subSubjects = subSubjects;
	}

	public Subject getParentSubject() {
		return parentSubject;
	}

	public void setParentSubject(Subject parentSubject) {
		this.parentSubject = parentSubject;
	}

}
