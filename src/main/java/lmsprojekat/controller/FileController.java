package lmsprojekat.controller;

import lmsprojekat.dto.FileDTO;
import lmsprojekat.model.File;
import lmsprojekat.repository.MessageRepository;
import lmsprojekat.repository.NotificationRepository;
import lmsprojekat.repository.forumrepo.PostRepository;
import lmsprojekat.repository.teachingrepo.TeachingMaterialRepository;
import lmsprojekat.service.FileService;
import lmsprojekat.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController extends BaseCrudController<FileDTO, Long> {

    private final FileService service;
    private final FileStorageService storageService;
    private final PostRepository postRepository;
    private final NotificationRepository notificationRepository;
    private final MessageRepository messageRepository;
    private final TeachingMaterialRepository teachingMaterialRepository;

    public FileController(FileService service,
                          FileStorageService storageService,
                          PostRepository postRepository,
                          NotificationRepository notificationRepository,
                          MessageRepository messageRepository,
                          TeachingMaterialRepository teachingMaterialRepository) {
        this.service = service;
        this.storageService = storageService;
        this.postRepository = postRepository;
        this.notificationRepository = notificationRepository;
        this.messageRepository = messageRepository;
        this.teachingMaterialRepository = teachingMaterialRepository;
    }

    @Override
    protected FileService getService() {
        return service;
    }

    @PostMapping("/upload")
    public FileDTO uploadFile(@RequestParam("file") MultipartFile file,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "postId", required = false) Long postId,
                              @RequestParam(value = "notificationId", required = false) Long notificationId,
                              @RequestParam(value = "messageId", required = false) Long messageId,
                              @RequestParam(value = "teachingMaterialId", required = false) Long teachingMaterialId) {

        String url = storageService.storeFile(file);

        File entity = new File();
        entity.setDescription(description);
        entity.setUrl(url);

        if (postId != null) {
            entity.setPost(postRepository.findById(postId)
                    .orElseThrow(() -> new RuntimeException("Post not found with id " + postId)));
        }
        if (notificationId != null) {
            entity.setNotification(notificationRepository.findById(notificationId)
                    .orElseThrow(() -> new RuntimeException("Notification not found with id " + notificationId)));
        }
        if (messageId != null) {
            entity.setMessage(messageRepository.findById(messageId)
                    .orElseThrow(() -> new RuntimeException("Message not found with id " + messageId)));
        }
        if (teachingMaterialId != null) {
            entity.setTeachingMaterial(teachingMaterialRepository.findById(teachingMaterialId)
                    .orElseThrow(() -> new RuntimeException("TeachingMaterial not found with id " + teachingMaterialId)));
        }

        return service.toDTO(service.getRepository().save(entity));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        File file = service.getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));

        String filename = file.getUrl();
        Resource resource = storageService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
