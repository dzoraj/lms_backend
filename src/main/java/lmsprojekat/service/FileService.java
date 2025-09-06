package lmsprojekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.FileDTO;
import lmsprojekat.dto.MessageDTO;
import lmsprojekat.dto.NotificationDTO;
import lmsprojekat.dto.forumdto.PostDTO;
import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.Message;
import lmsprojekat.model.Notification;
import lmsprojekat.model.forum.Post;
import lmsprojekat.model.teaching.EvaluationInstrument;
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.repository.FileRepository;
import lmsprojekat.repository.MessageRepository;
import lmsprojekat.repository.NotificationRepository;
import lmsprojekat.repository.forumrepo.PostRepository;
import lmsprojekat.repository.teachingrepo.EvaluationInstrumentRepository;
import lmsprojekat.repository.teachingrepo.TeachingMaterialRepository;

@Service
public class FileService extends AbstractCrudService<FileDTO, File, Long> {

    private final FileRepository fileRepository;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final MessageRepository messageRepository;
    private final TeachingMaterialRepository teachingMaterialRepository;
    private final EvaluationInstrumentRepository evaluationInstrumentRepository;

    public FileService(FileRepository fileRepository,
                       PostRepository postRepository,
                       NotificationRepository notificationRepository,
                       MessageRepository messageRepository,
                       TeachingMaterialRepository teachingMaterialRepository,
                       EvaluationInstrumentRepository evaluationInstrumentRepository) {
        this.fileRepository = fileRepository;
        this.postRepository = postRepository;
        this.notificationRepository = notificationRepository;
        this.messageRepository = messageRepository;
        this.teachingMaterialRepository = teachingMaterialRepository;
        this.evaluationInstrumentRepository = evaluationInstrumentRepository;
    }

    @Override
    protected FileRepository getRepository() {
        return fileRepository;
    }

    @Override
    protected FileDTO toDTO(File entity) {
        PostDTO postDTO = entity.getPost() != null ? convertPostToDTO(entity.getPost()) : null;
        NotificationDTO notificationDTO = entity.getNotification() != null ? convertNotificationToDTO(entity.getNotification()) : null;
        MessageDTO messageDTO = entity.getMessage() != null ? convertMessageToDTO(entity.getMessage()) : null;

        List<EvaluationInstrumentDTO> evaluationInstrumentDTOs = entity.getEvaluationInstruments() != null ?
                entity.getEvaluationInstruments().stream()
                        .map(this::convertEvaluationInstrumentToDTO)
                        .collect(Collectors.toList()) : null;

        TeachingMaterialDTO teachingMaterialDTO = entity.getTeachingMaterial() != null ?
                convertTeachingMaterialToDTO(entity.getTeachingMaterial()) : null;

        return new FileDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getUrl(),
                postDTO,
                notificationDTO,
                messageDTO,
                evaluationInstrumentDTOs,
                teachingMaterialDTO
        );
    }

    @Override
    protected File toEntity(FileDTO dto) {
        File entity = new File();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());

        if (dto.getPost() != null && dto.getPost().getId() != null) {
            Post post = postRepository.findById(dto.getPost().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + dto.getPost().getId()));
            entity.setPost(post);
        } else {
            entity.setPost(null);
        }

        if (dto.getNotification() != null && dto.getNotification().getId() != null) {
            Notification notification = notificationRepository.findById(dto.getNotification().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Notification not found with id " + dto.getNotification().getId()));
            entity.setNotification(notification);
        } else {
            entity.setNotification(null);
        }

        if (dto.getMessage() != null && dto.getMessage().getId() != null) {
            Message message = messageRepository.findById(dto.getMessage().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Message not found with id " + dto.getMessage().getId()));
            entity.setMessage(message);
        } else {
            entity.setMessage(null);
        }

        if (dto.getEvaluationInstruments() != null) {
            List<EvaluationInstrument> evaluationInstruments = dto.getEvaluationInstruments().stream()
                    .map(eiDto -> {
                        if (eiDto.getId() == null) {
                            throw new IllegalArgumentException("EvaluationInstrument id is required");
                        }
                        return evaluationInstrumentRepository.findById(eiDto.getId())
                                .orElseThrow(() -> new IllegalArgumentException("EvaluationInstrument not found with id " + eiDto.getId()));
                    })
                    .collect(Collectors.toList());
            entity.setEvaluationInstruments(evaluationInstruments);
        } else {
            entity.setEvaluationInstruments(null);
        }

        if (dto.getTeachingMaterial() != null && dto.getTeachingMaterial().getId() != null) {
            TeachingMaterial tm = teachingMaterialRepository.findById(dto.getTeachingMaterial().getId())
                    .orElseThrow(() -> new IllegalArgumentException("TeachingMaterial not found with id " + dto.getTeachingMaterial().getId()));
            entity.setTeachingMaterial(tm);
        } else {
            entity.setTeachingMaterial(null);
        }

        return entity;
    }

    @Override
    protected void updateEntity(File entity, FileDTO dto) {
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());

        // Update post
        if (dto.getPost() != null && dto.getPost().getId() != null) {
            Post post = postRepository.findById(dto.getPost().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Post not found with id " + dto.getPost().getId()));
            entity.setPost(post);
        } else {
            entity.setPost(null);
        }

        // Update notification
        if (dto.getNotification() != null && dto.getNotification().getId() != null) {
            Notification notification = notificationRepository.findById(dto.getNotification().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Notification not found with id " + dto.getNotification().getId()));
            entity.setNotification(notification);
        } else {
            entity.setNotification(null);
        }

        // Update message
        if (dto.getMessage() != null && dto.getMessage().getId() != null) {
            Message message = messageRepository.findById(dto.getMessage().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Message not found with id " + dto.getMessage().getId()));
            entity.setMessage(message);
        } else {
            entity.setMessage(null);
        }

        // Update evaluation instruments
        if (dto.getEvaluationInstruments() != null) {
            List<EvaluationInstrument> evaluationInstruments = dto.getEvaluationInstruments().stream()
                    .map(eiDto -> {
                        if (eiDto.getId() == null) {
                            throw new IllegalArgumentException("EvaluationInstrument id is required");
                        }
                        return evaluationInstrumentRepository.findById(eiDto.getId())
                                .orElseThrow(() -> new IllegalArgumentException("EvaluationInstrument not found with id " + eiDto.getId()));
                    })
                    .collect(Collectors.toList());
            entity.setEvaluationInstruments(evaluationInstruments);
        } else {
            entity.setEvaluationInstruments(null);
        }

        // Update teaching material
        if (dto.getTeachingMaterial() != null && dto.getTeachingMaterial().getId() != null) {
            TeachingMaterial tm = teachingMaterialRepository.findById(dto.getTeachingMaterial().getId())
                    .orElseThrow(() -> new IllegalArgumentException("TeachingMaterial not found with id " + dto.getTeachingMaterial().getId()));
            entity.setTeachingMaterial(tm);
        } else {
            entity.setTeachingMaterial(null);
        }
    }

    private PostDTO convertPostToDTO(Post post) {
        return new PostDTO(
                post.getId(),
                post.getPostingTime(),
                post.getContent(),
                post.getAuthor() != null ? post.getAuthor().getId() : null,
                post.getTopic() != null ? post.getTopic().getId() : null,
                post.getAttachments() != null ?
                        post.getAttachments().stream().map(File::getId).collect(Collectors.toList()) : null
        );
    }

    private NotificationDTO convertNotificationToDTO(Notification notification) {

        return new NotificationDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.getTimePosted(),
                null, 
                null, 
                null 
        );
    }

    private MessageDTO convertMessageToDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getDateSent(),
                message.getContent(),
                message.getSender() != null ? message.getSender().getId() : null,
                message.getReceiver() != null ? message.getReceiver().getId() : null,
                message.getAttachments() != null ? 
                        message.getAttachments().stream().map(File::getId).collect(Collectors.toList()) : null
        );
    }

    private EvaluationInstrumentDTO convertEvaluationInstrumentToDTO(EvaluationInstrument ei) {
        EvaluationInstrumentDTO dto = new EvaluationInstrumentDTO();
        dto.setId(ei.getId());
        dto.setName(ei.getName());
        return dto;
    }

    private TeachingMaterialDTO convertTeachingMaterialToDTO(TeachingMaterial tm) {
        return new TeachingMaterialDTO(
                tm.getId(),
                tm.getName(),
                tm.getAuthors(),
                tm.getYearOfPublication(),
                tm.getLearningOutcome() != null ? tm.getLearningOutcome().getId() : null,
                tm.getFiles() != null ? tm.getFiles().stream().map(File::getId).collect(Collectors.toList()) : null
        );
    }
}
