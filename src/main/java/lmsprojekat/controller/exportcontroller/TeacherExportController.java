package lmsprojekat.controller.exportcontroller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.service.exportservice.TeacherExportService;
import lmsprojekat.service.exportservice.PdfExportService;

@RestController
@RequestMapping("/api/export")
public class TeacherExportController {

    private final TeacherExportService service;
    private final PdfExportService pdf;

    public TeacherExportController(TeacherExportService service, PdfExportService pdf) {
        this.service = service;
        this.pdf = pdf;
    }

    @GetMapping(value = "/teachers.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportTeachersXml() throws Exception {
        String xml = service.toXml(service.buildTeachers());
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/teachers/{id}.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportOneTeacherXml(@PathVariable Long id) throws Exception {
        String xml = service.toXml(service.buildTeachers(id));
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/teacher.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> exportOneTeacherXmlParam(@RequestParam("id") Long id) throws Exception {
        String xml = service.toXml(service.buildTeachers(id));
        return ResponseEntity.ok(xml);
    }

    @GetMapping(value = "/teachers.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportTeachersPdf() throws Exception {
        var data = service.buildTeachers();
        if (data.isEmpty()) return ResponseEntity.noContent().build();
        byte[] pdfBytes = pdf.teachersToPdf(data);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=teachers.pdf")
            .body(pdfBytes);
    }

    @GetMapping(value = "/teachers/{id}.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportOneTeacherPdf(@PathVariable Long id) throws Exception {
        var data = service.buildTeachers(id);
        if (data.isEmpty()) return ResponseEntity.notFound().build();
        byte[] pdfBytes = pdf.teachersToPdf(data);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=teacher-" + id + ".pdf")
            .body(pdfBytes);
    }
}
