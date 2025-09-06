package lmsprojekat.dto.titledto;

import java.time.LocalDate;
import java.util.List;

import lmsprojekat.dto.userdto.TeacherDTO;

public class TitleDTO {

    private Long id;
    private LocalDate selectionDate;
    private LocalDate endDate;
    private TeacherDTO teacher;
    private List<ScientificFieldDTO> scientificFields;
    private List<TitleTypeDTO> titleTypes;

    public TitleDTO() {
    }

    public TitleDTO(Long id, LocalDate selectionDate, LocalDate endDate, TeacherDTO teacher,
                    List<ScientificFieldDTO> scientificFields, List<TitleTypeDTO> titleTypes) {
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

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public List<ScientificFieldDTO> getScientificFields() {
        return scientificFields;
    }

    public void setScientificFields(List<ScientificFieldDTO> scientificFields) {
        this.scientificFields = scientificFields;
    }

    public List<TitleTypeDTO> getTitleTypes() {
        return titleTypes;
    }

    public void setTitleTypes(List<TitleTypeDTO> titleTypes) {
        this.titleTypes = titleTypes;
    }
}
