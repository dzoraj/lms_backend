package lmsprojekat.dto.teachingdto;

import java.util.List;

import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;

public class EducationalGoalDTO {

    private Long id;
    private String description;
    private List<LearningOutcomeDTO> learningOutcomes;

    public EducationalGoalDTO() {}

    public EducationalGoalDTO(Long id, String description, List<LearningOutcomeDTO> learningOutcomes) {
        this.id = id;
        this.description = description;
        this.learningOutcomes = learningOutcomes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LearningOutcomeDTO> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(List<LearningOutcomeDTO> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}
