package lmsprojekat.controller.exportcontroller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lmsprojekat.dto.exportdto.ImportResult;
import lmsprojekat.dto.exportdto.ValidationReport;
import lmsprojekat.service.exportservice.EvaluationImportService;
import lmsprojekat.service.exportservice.EvaluationPersistorJpa.ImportMode;

@RestController
@RequestMapping("/api/import")
public class EvaluationImportController {

    private final EvaluationImportService importService;

    public EvaluationImportController(EvaluationImportService importService) {
        this.importService = importService;
    }

    @PostMapping(value = "/evaluations", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImportResult> importMultipart(
            @RequestPart(required = false, name = "file") MultipartFile file,
            @RequestPart(required = false, name = "xml") String xml,
            @RequestPart(required = false, name = "path") String path,
            @RequestParam(defaultValue = "MERGE") ImportMode mode
    ) throws Exception {
        String payload = resolveXml(file, xml, path);
        return ResponseEntity.ok(importService.importXml(payload, mode));
    }

    @PostMapping(value = "/evaluations/raw", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImportResult> importRaw(
            @RequestBody String xml,
            @RequestParam(defaultValue = "MERGE") ImportMode mode
    ) {
        return ResponseEntity.ok(importService.importXml(xml, mode));
    }

    @PostMapping(value = "/evaluations/validate", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationReport> validate(@RequestBody String xml) {
        try {
            importService.validateXml(xml);
            return ResponseEntity.ok(new ValidationReport(true, java.util.List.of()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ValidationReport(false, java.util.List.of(e.getMessage())));
        }
    }

    @GetMapping(value = "/evaluations/schema", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<byte[]> schema() throws Exception {
        ClassPathResource res = new ClassPathResource("xsd/evaluation.xsd");
        byte[] bytes = StreamUtils.copyToByteArray(res.getInputStream());
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=evaluation.xsd")
            .body(bytes);
    }

    private String resolveXml(MultipartFile file, String xml, String path) throws Exception {
        if (file != null && !file.isEmpty()) {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        }
        if (xml != null && !xml.isBlank()) {
            return xml;
        }
        if (path != null && !path.isBlank()) {
            Path base = Path.of("uploads").toAbsolutePath().normalize();
            Path requested = base.resolve(path).normalize();
            if (!requested.startsWith(base)) throw new IllegalArgumentException("Invalid path");
            return Files.readString(requested);
        }
        throw new IllegalArgumentException("Provide 'file', 'xml', or 'path'");
    }
}
