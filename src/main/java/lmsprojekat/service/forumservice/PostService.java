package lmsprojekat.service.forumservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.forumdto.PostDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.forum.Post;
import lmsprojekat.model.forum.Topic;
import lmsprojekat.model.forum.UserOnForum;
import lmsprojekat.repository.FileRepository;
import lmsprojekat.repository.forumrepo.PostRepository;
import lmsprojekat.repository.forumrepo.TopicRepository;
import lmsprojekat.repository.forumrepo.UserOnForumRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class PostService extends AbstractCrudService<PostDTO, Post, Long> {

    private final PostRepository postRepository;
    private final UserOnForumRepository userOnForumRepository;
    private final TopicRepository topicRepository;
    private final FileRepository fileRepository;

    public PostService(PostRepository postRepository, UserOnForumRepository userOnForumRepository,
                       TopicRepository topicRepository, FileRepository fileRepository) {
        this.postRepository = postRepository;
        this.userOnForumRepository = userOnForumRepository;
        this.topicRepository = topicRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    protected PostRepository getRepository() {
        return postRepository;
    }

    @Override
    protected PostDTO toDTO(Post entity) {
        List<Long> attachmentIds = entity.getAttachments() != null
                ? entity.getAttachments().stream().map(File::getId).collect(Collectors.toList())
                : List.of();

        return new PostDTO(
                entity.getId(),
                entity.getPostingTime(),
                entity.getContent(),
                entity.getAuthor().getId(),
                entity.getTopic().getId(),
                attachmentIds
        );
    }

    @Override
    protected Post toEntity(PostDTO dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setPostingTime(dto.getPostingTime());
        post.setContent(dto.getContent());

        UserOnForum author = userOnForumRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("UserOnForum not found"));
        post.setAuthor(author);

        Topic topic = topicRepository.findById(dto.getTopicId())
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
        post.setTopic(topic);

        if (dto.getAttachmentIds() != null) {
            List<File> attachments = dto.getAttachmentIds().stream()
                    .map(id -> fileRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("File not found with id " + id)))
                    .collect(Collectors.toList());
            post.setAttachments(attachments);
        }

        return post;
    }

    @Override
    protected void updateEntity(Post entity, PostDTO dto) {
        entity.setPostingTime(dto.getPostingTime());
        entity.setContent(dto.getContent());

        if (dto.getAuthorId() != null) {
            UserOnForum author = userOnForumRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("UserOnForum not found"));
            entity.setAuthor(author);
        }

        if (dto.getTopicId() != null) {
            Topic topic = topicRepository.findById(dto.getTopicId())
                    .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
            entity.setTopic(topic);
        }

        if (dto.getAttachmentIds() != null) {
            List<File> attachments = dto.getAttachmentIds().stream()
                    .map(id -> fileRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("File not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setAttachments(attachments);
        }
    }
}
