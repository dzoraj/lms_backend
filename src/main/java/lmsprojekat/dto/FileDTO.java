package lmsprojekat.dto;

import java.util.List;

public class FileDTO {
	private Long id;
	private String description;
	private String url;

	private Long postId;
	private Long notificationId;
	private Long messageId;
	private List<Long> evaluationInstrumentIds;
	private Long teachingMaterialId;

	public FileDTO() {
	}

	public FileDTO(Long id, String description, String url, Long postId, Long notificationId, Long messageId,
			List<Long> evaluationInstrumentIds, Long teachingMaterialId) {
		this.id = id;
		this.description = description;
		this.url = url;
		this.postId = postId;
		this.notificationId = notificationId;
		this.messageId = messageId;
		this.evaluationInstrumentIds = evaluationInstrumentIds;
		this.teachingMaterialId = teachingMaterialId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public List<Long> getEvaluationInstrumentIds() {
		return evaluationInstrumentIds;
	}

	public void setEvaluationInstrumentIds(List<Long> evaluationInstrumentIds) {
		this.evaluationInstrumentIds = evaluationInstrumentIds;
	}

	public Long getTeachingMaterialId() {
		return teachingMaterialId;
	}

	public void setTeachingMaterialId(Long teachingMaterialId) {
		this.teachingMaterialId = teachingMaterialId;
	}

}
