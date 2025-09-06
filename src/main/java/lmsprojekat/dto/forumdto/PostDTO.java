package lmsprojekat.dto.forumdto;

import java.time.LocalDate;
import java.util.List;

public class PostDTO {
    private Long id;
    private LocalDate postingTime;
    private String content;
    private Long authorId;
    private Long topicId;
    private List<Long> attachmentIds;

    public PostDTO() {}

    public PostDTO(Long id, LocalDate postingTime, String content, Long authorId, Long topicId, List<Long> attachmentIds) {
        this.id = id;
        this.postingTime = postingTime;
        this.content = content;
        this.authorId = authorId;
        this.topicId = topicId;
        this.attachmentIds = attachmentIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(LocalDate postingTime) {
        this.postingTime = postingTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public List<Long> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<Long> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
