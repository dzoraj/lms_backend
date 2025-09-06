package lmsprojekat.dto;

import java.util.List;

import lmsprojekat.dto.forumdto.PostDTO;
import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;

public class FileDTO {
    private Long id;
    private String description;
    private String url;
    private PostDTO post;
    private NotificationDTO notification;
    private MessageDTO message;
    private List<EvaluationInstrumentDTO> evaluationInstruments;
    private TeachingMaterialDTO teachingMaterial;

    public FileDTO() {}

    public FileDTO(Long id, String description, String url, PostDTO post, NotificationDTO notification,
                   MessageDTO message, List<EvaluationInstrumentDTO> evaluationInstruments,
                   TeachingMaterialDTO teachingMaterial) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.post = post;
        this.notification = notification;
        this.message = message;
        this.evaluationInstruments = evaluationInstruments;
        this.teachingMaterial = teachingMaterial;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public PostDTO getPost() { return post; }
    public void setPost(PostDTO post) { this.post = post; }

    public NotificationDTO getNotification() { return notification; }
    public void setNotification(NotificationDTO notification) { this.notification = notification; }

    public MessageDTO getMessage() { return message; }
    public void setMessage(MessageDTO message) { this.message = message; }

    public List<EvaluationInstrumentDTO> getEvaluationInstruments() { return evaluationInstruments; }
    public void setEvaluationInstruments(List<EvaluationInstrumentDTO> evaluationInstruments) { this.evaluationInstruments = evaluationInstruments; }

    public TeachingMaterialDTO getTeachingMaterial() { return teachingMaterial; }
    public void setTeachingMaterial(TeachingMaterialDTO teachingMaterial) { this.teachingMaterial = teachingMaterial; }
}
