package lmsprojekat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.model.users.Student;
import lmsprojekat.repository.userrepo.StudentRepository;
import lmsprojekat.service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final StudentRepository studentRepository;

    public DocumentController(DocumentService documentService, StudentRepository studentRepository) {
        this.documentService = documentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student-confirmation/{studentId}")
    public ResponseEntity<byte[]> generateStudentConfirmation(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Map<String, Object> vars = new HashMap<>();
        vars.put("name", student.getName());
        vars.put("indexNumber", studentRepository.latestIndex(student.getId()).stream().findFirst().orElse("N/A"));
        vars.put("studyYear", studentRepository.latestEnrollmentYear(student.getId()).stream().findFirst().orElse(null));

        byte[] pdf = documentService.generateDocument("regular-student", vars);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=confirmation.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
