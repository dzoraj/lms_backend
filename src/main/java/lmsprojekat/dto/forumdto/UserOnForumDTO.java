package lmsprojekat.dto.forumdto;

import java.util.List;

public class UserOnForumDTO {
    private Long id;
    private Long roleId;
    private Long forumId;
    private Long registeredUserId;
    private List<Long> topicIds;
    private List<Long> postIds;

    public UserOnForumDTO() {}

    public UserOnForumDTO(Long id, Long roleId, Long forumId, Long registeredUserId,
                          List<Long> topicIds, List<Long> postIds) {
        this.id = id;
        this.roleId = roleId;
        this.forumId = forumId;
        this.registeredUserId = registeredUserId;
        this.topicIds = topicIds;
        this.postIds = postIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(Long registeredUserId) {
        this.registeredUserId = registeredUserId;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }

    public List<Long> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Long> postIds) {
        this.postIds = postIds;
    }
}
