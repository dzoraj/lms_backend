package lmsprojekat.dto;

import java.util.Date;
import java.util.List;

public class MessageDTO {
    private Long id;
    private Date dateSent;
    private String content;
    private Long senderId;
    private Long receiverId;
    private List<Long> attachmentIds;

    public MessageDTO() {}

    public MessageDTO(Long id, Date dateSent, String content, Long senderId, Long receiverId, List<Long> attachmentIds) {
        this.id = id;
        this.dateSent = dateSent;
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.attachmentIds = attachmentIds;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public List<Long> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<Long> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}


}
