package lmsprojekat.dto.studentdto;
public class StudyProgramDTO {
    private Long id;
    private String name;
    private Long leaderId;
    private Long facultyId;
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
	public Long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}
	public Long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
	public StudyProgramDTO(Long id, String name, Long leaderId, Long facultyId) {
		super();
		this.id = id;
		this.name = name;
		this.leaderId = leaderId;
		this.facultyId = facultyId;
	}
	public StudyProgramDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}