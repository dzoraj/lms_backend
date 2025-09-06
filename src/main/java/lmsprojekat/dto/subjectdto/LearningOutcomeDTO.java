package lmsprojekat.dto.subjectdto;

import java.util.List;

import lmsprojekat.dto.teachingdto.EducationalGoalDTO;
import lmsprojekat.dto.teachingdto.KnowledgeEvaluationDTO;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;

public class LearningOutcomeDTO {

    private Long id;
    private String description; 
    private SubjectDTO subject;
    private List<EducationalGoalDTO> educationalGoals;
    private List<TeachingMaterialDTO> teachingMaterials;
    private List<KnowledgeEvaluationDTO> knowledgeEvaluations;
    private List<TeachingSessionDTO> teachingSessions;

    public LearningOutcomeDTO() {}

    public LearningOutcomeDTO(Long id, String description, SubjectDTO subject, List<EducationalGoalDTO> educationalGoals,
            List<TeachingMaterialDTO> teachingMaterials, List<KnowledgeEvaluationDTO> knowledgeEvaluations,
            List<TeachingSessionDTO> teachingSessions) {
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.educationalGoals = educationalGoals;
        this.teachingMaterials = teachingMaterials;
        this.knowledgeEvaluations = knowledgeEvaluations;
        this.teachingSessions = teachingSessions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public SubjectDTO getSubject() { return subject; }
    public void setSubject(SubjectDTO subject) { this.subject = subject; }

    public List<EducationalGoalDTO> getEducationalGoals() { return educationalGoals; }
    public void setEducationalGoals(List<EducationalGoalDTO> educationalGoals) { this.educationalGoals = educationalGoals; }

    public List<TeachingMaterialDTO> getTeachingMaterials() { return teachingMaterials; }
    public void setTeachingMaterials(List<TeachingMaterialDTO> teachingMaterials) { this.teachingMaterials = teachingMaterials; }

    public List<KnowledgeEvaluationDTO> getKnowledgeEvaluations() { return knowledgeEvaluations; }
    public void setKnowledgeEvaluations(List<KnowledgeEvaluationDTO> knowledgeEvaluations) { this.knowledgeEvaluations = knowledgeEvaluations; }

    public List<TeachingSessionDTO> getTeachingSessions() { return teachingSessions; }
    public void setTeachingSessions(List<TeachingSessionDTO> teachingSessions) { this.teachingSessions = teachingSessions; }

}
