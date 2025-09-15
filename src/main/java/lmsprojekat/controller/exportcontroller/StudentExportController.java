package lmsprojekat.controller.exportcontroller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.service.exportservice.StudentExportService;
import lmsprojekat.service.exportservice.PdfExportService;
import lmsprojekat.export.xml.StudentXml;

import java.util.List;

@RestController
@RequestMapping("/api/export")
public class StudentExportController {

    private final StudentExportService service;
    private final PdfExportService pdf;

    public StudentExportController(StudentExportService service, PdfExportService pdf) {
        this.service = service;
        this.pdf = pdf;
    }

    @GetMapping(value = "/students.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportStudentsXml() throws Exception {
        String xml = service.toXml(service.buildStudents());
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/students/{id}.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportOneStudentXml(@PathVariable Long id) throws Exception {
        List<StudentXml> list = service.buildStudents(id);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("<error>No student found with id " + id + "</error>");
        }
        String xml = service.toXml(list);
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/students.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportStudentsPdf() throws Exception {
        var data = service.buildStudents();
        if (data.isEmpty()) return ResponseEntity.noContent().build();
        byte[] pdfBytes = pdf.studentsToPdf(data);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=students.pdf")
                .body(pdfBytes);
    }

    @GetMapping(value = "/students/{id}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportOneStudentPdf(@PathVariable Long id) throws Exception {
        var data = service.buildStudents(id);
        if (data.isEmpty()) return ResponseEntity.notFound().build();
        byte[] pdfBytes = pdf.studentsToPdf(data);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=student-" + id + ".pdf")
                .body(pdfBytes);
    }
}
