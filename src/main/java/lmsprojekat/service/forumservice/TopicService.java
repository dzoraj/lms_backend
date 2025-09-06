package lmsprojekat.service.forumservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.forumdto.TopicDTO;
import lmsprojekat.model.forum.Forum;
import lmsprojekat.model.forum.Post;
import lmsprojekat.model.forum.Topic;
import lmsprojekat.model.forum.UserOnForum;
import lmsprojekat.repository.forumrepo.ForumRepository;
import lmsprojekat.repository.forumrepo.PostRepository;
import lmsprojekat.repository.forumrepo.TopicRepository;
import lmsprojekat.repository.forumrepo.UserOnForumRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class TopicService extends AbstractCrudService<TopicDTO, Topic, Long> {

    private final TopicRepository topicRepository;
    private final UserOnForumRepository userOnForumRepository;
    private final ForumRepository forumRepository;
    private final PostRepository postRepository;

    public TopicService(TopicRepository topicRepository, UserOnForumRepository userOnForumRepository,
                        ForumRepository forumRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.userOnForumRepository = userOnForumRepository;
        this.forumRepository = forumRepository;
        this.postRepository = postRepository;
    }

    @Override
    protected TopicRepository getRepository() {
        return topicRepository;
    }

    @Override
    protected TopicDTO toDTO(Topic entity) {
        List<Long> postIds = entity.getPosts() != null
                ? entity.getPosts().stream().map(Post::getId).collect(Collectors.toList())
                : List.of();

        return new TopicDTO(
                entity.getId(),
                entity.getName(),
                entity.getAuthor().getId(),
                entity.getForum().getId(),
                postIds
        );
    }

    @Override
    protected Topic toEntity(TopicDTO dto) {
        Topic topic = new Topic();
        topic.setId(dto.getId());
        topic.setName(dto.getName());

        UserOnForum author = userOnForumRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("UserOnForum not found"));
        topic.setAuthor(author);

        Forum forum = forumRepository.findById(dto.getForumId())
                .orElseThrow(() -> new IllegalArgumentException("Forum not found"));
        topic.setForum(forum);

        if (dto.getPostIds() != null) {
            List<Post> posts = dto.getPostIds().stream()
                    .map(id -> postRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + id)))
                    .collect(Collectors.toList());
            topic.setPosts(posts);
        }

        return topic;
    }

    @Override
    protected void updateEntity(Topic entity, TopicDTO dto) {
        entity.setName(dto.getName());

        if (dto.getAuthorId() != null) {
            UserOnForum author = userOnForumRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("UserOnForum not found"));
            entity.setAuthor(author);
        }

        if (dto.getForumId() != null) {
            Forum forum = forumRepository.findById(dto.getForumId())
                    .orElseThrow(() -> new IllegalArgumentException("Forum not found"));
            entity.setForum(forum);
        }

        if (dto.getPostIds() != null) {
            List<Post> posts = dto.getPostIds().stream()
                    .map(id -> postRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setPosts(posts);
        }
    }
}
