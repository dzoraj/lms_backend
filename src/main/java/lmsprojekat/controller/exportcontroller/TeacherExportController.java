package lmsprojekat.controller.exportcontroller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.service.exportservice.TeacherExportService;

@RestController
@RequestMapping("/api/export")
public class TeacherExportController {

    private final TeacherExportService service;

    public TeacherExportController(TeacherExportService service) {
        this.service = service;
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
}
