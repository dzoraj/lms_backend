package lmsprojekat.service.forumservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.forumdto.ForumDTO;
import lmsprojekat.model.forum.Forum;
import lmsprojekat.model.forum.Topic;
import lmsprojekat.repository.forumrepo.ForumRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class ForumService extends AbstractCrudService<ForumDTO, Forum, Long> {
    private final ForumRepository forumRepository;

    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @Override
    protected ForumRepository getRepository() {
        return forumRepository;
    }

    @Override
    protected ForumDTO toDTO(Forum entity) {
        List<Long> topicIds = entity.getTopics() != null
                ? entity.getTopics().stream()
                    .map(Topic::getId)
                    .collect(Collectors.toList())
                : List.of();

        return new ForumDTO(entity.getId(), entity.getJavni(), topicIds);
    }

    @Override
    protected Forum toEntity(ForumDTO dto) {
        Forum forum = new Forum();
        forum.setId(dto.getId());
        forum.setJavni(dto.getJavni());
        return forum;
    }

    @Override
    protected void updateEntity(Forum entity, ForumDTO dto) {
        entity.setJavni(dto.getJavni());
    }
}
