package lmsprojekat.model.title;

//Zvanje
//- datumIzbora : date
//- datumPrestanka : date


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.SoftDeletableEntity;
import lmsprojekat.model.users.Teacher;

@Entity
public class Title extends SoftDeletableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private LocalDate selectionDate;

    @Column(nullable = true)  
    private LocalDate endDate;

    @ManyToOne(optional=true)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany
    private List<ScientificField> scientificFields;

    @OneToMany
    private List<TitleType> titleTypes;

	public Title() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Title(Long id, LocalDate selectionDate, LocalDate endDate, Teacher teacher,
			List<ScientificField> scientificFields, List<TitleType> titleTypes) {
		super();
		this.id = id;
		this.selectionDate = selectionDate;
		this.endDate = endDate;
		this.teacher = teacher;
		this.scientificFields = scientificFields;
		this.titleTypes = titleTypes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getSelectionDate() {
		return selectionDate;
	}

	public void setSelectionDate(LocalDate selectionDate) {
		this.selectionDate = selectionDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<ScientificField> getScientificFields() {
		return scientificFields;
	}

	public void setScientificFields(List<ScientificField> scientificFields) {
		this.scientificFields = scientificFields;
	}

	public List<TitleType> getTitleTypes() {
		return titleTypes;
	}

	public void setTitleTypes(List<TitleType> titleTypes) {
		this.titleTypes = titleTypes;
	}
}
