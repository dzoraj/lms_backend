package lmsprojekat.dto.subjectdto;

import java.util.List;

public class SubjectFullDTO {
    private Long id;
    private String name;
    private Integer espb;
    private Boolean mandatory;
    private Integer lectureCount;
    private Integer labCount;

    private List<LearningOutcomeFullDTO> syllabus;

	public SubjectFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubjectFullDTO(Long id, String name, Integer espb, Boolean mandatory, Integer lectureCount, Integer labCount,
			List<LearningOutcomeFullDTO> syllabus) {
		super();
		this.id = id;
		this.name = name;
		this.espb = espb;
		this.mandatory = mandatory;
		this.lectureCount = lectureCount;
		this.labCount = labCount;
		this.syllabus = syllabus;
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

	public List<LearningOutcomeFullDTO> getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(List<LearningOutcomeFullDTO> syllabus) {
		this.syllabus = syllabus;
	}
    
}
