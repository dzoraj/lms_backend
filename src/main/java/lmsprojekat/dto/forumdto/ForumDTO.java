package lmsprojekat.dto.forumdto;

import java.util.List;

public class ForumDTO {
    private Long id;
    private Boolean javni;
    private List<Long> topicIds;

    public ForumDTO() {}

    public ForumDTO(Long id, Boolean javni, List<Long> topicIds) {
        this.id = id;
        this.javni = javni;
        this.topicIds = topicIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getJavni() {
        return javni;
    }

    public void setJavni(Boolean javni) {
        this.javni = javni;
    }

    public List<Long> getTopicIds() {
        return topicIds;
    }

    public void setTopicIds(List<Long> topicIds) {
        this.topicIds = topicIds;
    }
}
