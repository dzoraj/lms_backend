package lmsprojekat.dto.subjectdto;

import java.util.List;

import lmsprojekat.dto.studentdto.StudyYearDTO;

public class SubjectDTO {

    private Long id;
    private String name;
    private Integer espb;
    private Boolean mandatory;
    private Integer lectureCount;
    private Integer labCount;
    private Integer otherTeachingForms;
    private Integer researchWork;
    private Integer otherClasses;
    private StudyYearDTO studyYear;
    private List<LearningOutcomeDTO> syllabus;
    private List<SubjectDTO> subSubjects;
    private SubjectDTO parentSubject;

    public SubjectDTO() {}

    public SubjectDTO(Long id, String name, Integer espb, Boolean mandatory, Integer lectureCount, Integer labCount,
            Integer otherTeachingForms, Integer researchWork, Integer otherClasses, StudyYearDTO studyYear,
            List<LearningOutcomeDTO> syllabus, List<SubjectDTO> subSubjects, SubjectDTO parentSubject) {
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getEspb() { return espb; }
    public void setEspb(Integer espb) { this.espb = espb; }

    public Boolean getMandatory() { return mandatory; }
    public void setMandatory(Boolean mandatory) { this.mandatory = mandatory; }

    public Integer getLectureCount() { return lectureCount; }
    public void setLectureCount(Integer lectureCount) { this.lectureCount = lectureCount; }

    public Integer getLabCount() { return labCount; }
    public void setLabCount(Integer labCount) { this.labCount = labCount; }

    public Integer getOtherTeachingForms() { return otherTeachingForms; }
    public void setOtherTeachingForms(Integer otherTeachingForms) { this.otherTeachingForms = otherTeachingForms; }

    public Integer getResearchWork() { return researchWork; }
    public void setResearchWork(Integer researchWork) { this.researchWork = researchWork; }

    public Integer getOtherClasses() { return otherClasses; }
    public void setOtherClasses(Integer otherClasses) { this.otherClasses = otherClasses; }

    public StudyYearDTO getStudyYear() { return studyYear; }
    public void setStudyYear(StudyYearDTO studyYear) { this.studyYear = studyYear; }

    public List<LearningOutcomeDTO> getSyllabus() { return syllabus; }
    public void setSyllabus(List<LearningOutcomeDTO> syllabus) { this.syllabus = syllabus; }

    public List<SubjectDTO> getSubSubjects() { return subSubjects; }
    public void setSubSubjects(List<SubjectDTO> subSubjects) { this.subSubjects = subSubjects; }

    public SubjectDTO getParentSubject() { return parentSubject;
    
    }
    }
