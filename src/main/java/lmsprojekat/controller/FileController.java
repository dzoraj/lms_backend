package lmsprojekat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.FileDTO;
import lmsprojekat.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController extends BaseCrudController<FileDTO, Long> {
    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @Override
    protected FileService getService() {
        return service;
    }
}