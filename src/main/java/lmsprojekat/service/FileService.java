package lmsprojekat.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.FileDTO;
import lmsprojekat.model.File;
import lmsprojekat.model.teaching.EvaluationInstrument;
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
        return new FileDTO(
                entity.getId(),
                entity.getDescription(),
                entity.getUrl(),
                entity.getPost() != null ? entity.getPost().getId() : null,
                entity.getNotification() != null ? entity.getNotification().getId() : null,
                entity.getMessage() != null ? entity.getMessage().getId() : null,
                entity.getEvaluationInstruments() != null
                        ? entity.getEvaluationInstruments().stream().map(EvaluationInstrument::getId).toList()
                        : null,
                entity.getTeachingMaterial() != null ? entity.getTeachingMaterial().getId() : null
        );
    }

    @Override
    protected File toEntity(FileDTO dto) {
        File entity = new File();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());

        if (dto.getPostId() != null) {
            entity.setPost(postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + dto.getPostId())));
        }

        if (dto.getNotificationId() != null) {
            entity.setNotification(notificationRepository.findById(dto.getNotificationId())
                    .orElseThrow(() -> new EntityNotFoundException("Notification not found with id " + dto.getNotificationId())));
        }

        if (dto.getMessageId() != null) {
            entity.setMessage(messageRepository.findById(dto.getMessageId())
                    .orElseThrow(() -> new EntityNotFoundException("Message not found with id " + dto.getMessageId())));
        }

        if (dto.getEvaluationInstrumentIds() != null) {
            List<EvaluationInstrument> instruments = dto.getEvaluationInstrumentIds().stream()
                    .map(id -> evaluationInstrumentRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("EvaluationInstrument not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setEvaluationInstruments(instruments);
        }

        if (dto.getTeachingMaterialId() != null) {
            entity.setTeachingMaterial(teachingMaterialRepository.findById(dto.getTeachingMaterialId())
                    .orElseThrow(() -> new EntityNotFoundException("TeachingMaterial not found with id " + dto.getTeachingMaterialId())));
        }

        return entity;
    }

    @Override
    protected void updateEntity(File entity, FileDTO dto) {
        entity.setDescription(dto.getDescription());
        entity.setUrl(dto.getUrl());

        if (dto.getPostId() != null) {
            entity.setPost(postRepository.findById(dto.getPostId())
                    .orElseThrow(() -> new EntityNotFoundException("Post not found with id " + dto.getPostId())));
        } else {
            entity.setPost(null);
        }

        if (dto.getNotificationId() != null) {
            entity.setNotification(notificationRepository.findById(dto.getNotificationId())
                    .orElseThrow(() -> new EntityNotFoundException("Notification not found with id " + dto.getNotificationId())));
        } else {
            entity.setNotification(null);
        }

        if (dto.getMessageId() != null) {
            entity.setMessage(messageRepository.findById(dto.getMessageId())
                    .orElseThrow(() -> new EntityNotFoundException("Message not found with id " + dto.getMessageId())));
        } else {
            entity.setMessage(null);
        }

        if (dto.getEvaluationInstrumentIds() != null) {
            List<EvaluationInstrument> instruments = dto.getEvaluationInstrumentIds().stream()
                    .map(id -> evaluationInstrumentRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("EvaluationInstrument not found with id " + id)))
                    .collect(Collectors.toList());
            entity.setEvaluationInstruments(instruments);
        } else {
            entity.setEvaluationInstruments(null);
        }

        if (dto.getTeachingMaterialId() != null) {
            entity.setTeachingMaterial(teachingMaterialRepository.findById(dto.getTeachingMaterialId())
                    .orElseThrow(() -> new EntityNotFoundException("TeachingMaterial not found with id " + dto.getTeachingMaterialId())));
        } else {
            entity.setTeachingMaterial(null);
        }
    }
}
