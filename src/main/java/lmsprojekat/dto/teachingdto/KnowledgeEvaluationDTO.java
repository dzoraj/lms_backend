package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;
import java.util.List;
import lmsprojekat.model.subject.CourseRealization;
import lmsprojekat.model.subject.LearningOutcome;

public class KnowledgeEvaluationDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer points;
    private EvaluationInstrumentDTO evaluationInstrument;
    private EvaluationTypeDTO evaluationType;
    private CourseRealization courseRealization;
    private List<LearningOutcome> learningOutcomes;

    public KnowledgeEvaluationDTO() {}

    public KnowledgeEvaluationDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer points,
            EvaluationInstrumentDTO evaluationInstrument, EvaluationTypeDTO evaluationType,
            CourseRealization courseRealization, List<LearningOutcome> learningOutcomes) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.points = points;
        this.evaluationInstrument = evaluationInstrument;
        this.evaluationType = evaluationType;
        this.courseRealization = courseRealization;
        this.learningOutcomes = learningOutcomes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public EvaluationInstrumentDTO getEvaluationInstrument() {
        return evaluationInstrument;
    }

    public void setEvaluationInstrument(EvaluationInstrumentDTO evaluationInstrument) {
        this.evaluationInstrument = evaluationInstrument;
    }

    public EvaluationTypeDTO getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(EvaluationTypeDTO evaluationType) {
        this.evaluationType = evaluationType;
    }

    public CourseRealization getCourseRealization() {
        return courseRealization;
    }

    public void setCourseRealization(CourseRealization courseRealization) {
        this.courseRealization = courseRealization;
    }

    public List<LearningOutcome> getLearningOutcomes() {
        return learningOutcomes;
    }

    public void setLearningOutcomes(List<LearningOutcome> learningOutcomes) {
        this.learningOutcomes = learningOutcomes;
    }
}
