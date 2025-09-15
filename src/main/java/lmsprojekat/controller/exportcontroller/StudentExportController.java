// src/main/java/lmsprojekat/controller/exportcontroller/StudentExportController.java
package lmsprojekat.controller.exportcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.service.exportservice.StudentExportService;
import lmsprojekat.export.xml.StudentXml;

import java.util.List;

@RestController
@RequestMapping("/api/export")
public class StudentExportController {

    private final StudentExportService service;

    public StudentExportController(StudentExportService service) {
        this.service = service;
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
}
