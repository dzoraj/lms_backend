package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;
import java.util.List;

public class TeachingSessionDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long courseRealizationId;
    private TeachingTypeDTO teachingType;
    private List<Long> learningOutcomeIds;

    public TeachingSessionDTO() {}

    public TeachingSessionDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, Long courseRealizationId,
                              TeachingTypeDTO teachingType, List<Long> learningOutcomeIds) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseRealizationId = courseRealizationId;
        this.teachingType = teachingType;
        this.learningOutcomeIds = learningOutcomeIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Long getCourseRealizationId() { return courseRealizationId; }
    public void setCourseRealizationId(Long courseRealizationId) { this.courseRealizationId = courseRealizationId; }

    public TeachingTypeDTO getTeachingType() { return teachingType; }
    public void setTeachingType(TeachingTypeDTO teachingType) { this.teachingType = teachingType; }

    public List<Long> getLearningOutcomeIds() { return learningOutcomeIds; }
    public void setLearningOutcomeIds(List<Long> learningOutcomeIds) { this.learningOutcomeIds = learningOutcomeIds; }
}
