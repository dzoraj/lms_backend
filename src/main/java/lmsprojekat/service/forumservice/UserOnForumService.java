package lmsprojekat.service.forumservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.forumdto.UserOnForumDTO;
import lmsprojekat.model.forum.*;
import lmsprojekat.model.users.*;
import lmsprojekat.repository.forumrepo.*;
import lmsprojekat.repository.userrepo.RegisteredUserRepository;
import lmsprojekat.repository.userrepo.RoleRepository;
import lmsprojekat.service.AbstractCrudService;
@Service
public class UserOnForumService extends AbstractCrudService<UserOnForumDTO, UserOnForum, Long> {

    private final UserOnForumRepository userOnForumRepository;
    private final RoleRepository roleRepository;
    private final ForumRepository forumRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;

    public UserOnForumService(
            UserOnForumRepository userOnForumRepository,
            RoleRepository roleRepository,
            ForumRepository forumRepository,
            RegisteredUserRepository registeredUserRepository,
            TopicRepository topicRepository,
            PostRepository postRepository
    ) {
        this.userOnForumRepository = userOnForumRepository;
        this.roleRepository = roleRepository;
        this.forumRepository = forumRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
    }

    @Override
    protected UserOnForumRepository getRepository() {
        return userOnForumRepository;
    }

    @Override
    protected UserOnForumDTO toDTO(UserOnForum entity) {
        List<Long> topicIds = entity.getTopics() != null
                ? entity.getTopics().stream().map(Topic::getId).collect(Collectors.toList())
                : List.of();

        List<Long> postIds = entity.getPosts() != null
                ? entity.getPosts().stream().map(Post::getId).collect(Collectors.toList())
                : List.of();

        return new UserOnForumDTO(
                entity.getId(),
                entity.getRole().getId(),
                entity.getForum().getId(),
                entity.getRegisteredUser().getId(),
                topicIds,
                postIds
        );
    }

    @Override
    protected UserOnForum toEntity(UserOnForumDTO dto) {
        UserOnForum entity = new UserOnForum();
        entity.setId(dto.getId());

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        entity.setRole(role);

        Forum forum = forumRepository.findById(dto.getForumId())
                .orElseThrow(() -> new IllegalArgumentException("Forum not found"));
        entity.setForum(forum);

        RegisteredUser user = registeredUserRepository.findById(dto.getRegisteredUserId())
                .orElseThrow(() -> new IllegalArgumentException("RegisteredUser not found"));
        entity.setRegisteredUser(user);

        if (dto.getTopicIds() != null) {
            List<Topic> topics = dto.getTopicIds().stream()
                    .map(id -> topicRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Topic not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setTopics(topics);
        }

        if (dto.getPostIds() != null) {
            List<Post> posts = dto.getPostIds().stream()
                    .map(id -> postRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setPosts(posts);
        }

        return entity;
    }

    @Override
    protected void updateEntity(UserOnForum entity, UserOnForumDTO dto) {
        if (dto.getRoleId() != null) {
            Role role = roleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found"));
            entity.setRole(role);
        }

        if (dto.getForumId() != null) {
            Forum forum = forumRepository.findById(dto.getForumId())
                    .orElseThrow(() -> new IllegalArgumentException("Forum not found"));
            entity.setForum(forum);
        }

        if (dto.getRegisteredUserId() != null) {
            RegisteredUser user = registeredUserRepository.findById(dto.getRegisteredUserId())
                    .orElseThrow(() -> new IllegalArgumentException("RegisteredUser not found"));
            entity.setRegisteredUser(user);
        }

        if (dto.getTopicIds() != null) {
            List<Topic> topics = dto.getTopicIds().stream()
                    .map(id -> topicRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Topic not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setTopics(topics);
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
