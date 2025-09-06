package lmsprojekat.model.student;
//StudijskiProgram
//- naziv : String
//- rukovodilac 1
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.university.Faculty;
import lmsprojekat.model.users.Teacher;

@Entity
public class StudyProgram extends SoftDeletableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    
    @OneToOne
    @JoinColumn(name = "leader_id", nullable = true)
    private Teacher leader;
    //vodja-rukovodilac 
    
    
	@OneToMany(mappedBy = "studyProgram")
	private List<StudyYear> studyYear;
	
	
	@ManyToOne
	@JoinColumn(name = "faculty_id", nullable = true)
	private Faculty faculty;

	public StudyProgram() {
		super();
		// TODO Auto-generated constructor stub
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

	public Teacher getLeader() {
		return leader;
	}

	public void setLeader(Teacher leader) {
		this.leader = leader;
	}

	public List<StudyYear> getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(List<StudyYear> studyYear) {
		this.studyYear = studyYear;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public StudyProgram(Long id, String name, Teacher leader, List<StudyYear> studyYear, Faculty faculty) {
		super();
		this.id = id;
		this.name = name;
		this.leader = leader;
		this.studyYear = studyYear;
		this.faculty = faculty;
	}



	
}
