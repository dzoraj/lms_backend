package lmsprojekat.dto.subjectdto;

import java.util.List;

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
	private Long studyYearId;
	private List<Long> syllabusIds;
	private List<Long> subSubjectIds;
	private Long parentSubjectId;
	private Long gradingSchemeId;

	public SubjectDTO() {
	}

	public SubjectDTO(Long id, String name, Integer espb, Boolean mandatory, Integer lectureCount, Integer labCount,
			Integer otherTeachingForms, Integer researchWork, Integer otherClasses, Long studyYearId,
			List<Long> syllabusIds, List<Long> subSubjectIds, Long parentSubjectId, Long gradingSchemeId) {
		this.id = id;
		this.name = name;
		this.espb = espb;
		this.mandatory = mandatory;
		this.lectureCount = lectureCount;
		this.labCount = labCount;
		this.otherTeachingForms = otherTeachingForms;
		this.researchWork = researchWork;
		this.otherClasses = otherClasses;
		this.studyYearId = studyYearId;
		this.syllabusIds = syllabusIds;
		this.subSubjectIds = subSubjectIds;
		this.parentSubjectId = parentSubjectId;
		this.gradingSchemeId = gradingSchemeId;
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

	public Long getStudyYearId() {
		return studyYearId;
	}

	public void setStudyYearId(Long studyYearId) {
		this.studyYearId = studyYearId;
	}

	public List<Long> getSyllabusIds() {
		return syllabusIds;
	}

	public void setSyllabusIds(List<Long> syllabusIds) {
		this.syllabusIds = syllabusIds;
	}

	public List<Long> getSubSubjectIds() {
		return subSubjectIds;
	}

	public void setSubSubjectIds(List<Long> subSubjectIds) {
		this.subSubjectIds = subSubjectIds;
	}

	public Long getParentSubjectId() {
		return parentSubjectId;
	}

	public void setParentSubjectId(Long parentSubjectId) {
		this.parentSubjectId = parentSubjectId;
	}

	public Long getGradingSchemeId() {
		return gradingSchemeId;
	}

	public void setGradingSchemeId(Long gradingSchemeId) {
		this.gradingSchemeId = gradingSchemeId;
	}

}
