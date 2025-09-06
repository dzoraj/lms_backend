package lmsprojekat.dto.forumdto;

import java.util.List;

public class TopicDTO {
    private Long id;
    private String name;
    private Long authorId;
    private Long forumId;
    private List<Long> postIds;

    public TopicDTO() {}

    public TopicDTO(Long id, String name, Long authorId, Long forumId, List<Long> postIds) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.forumId = forumId;
        this.postIds = postIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    public List<Long> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Long> postIds) {
        this.postIds = postIds;
    }
}
