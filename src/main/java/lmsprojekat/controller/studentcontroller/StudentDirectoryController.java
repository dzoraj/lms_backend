package lmsprojekat.controller.studentcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.studentdto.StudentProfileDTO;
import lmsprojekat.dto.studentdto.StudentSearchDTO;
import lmsprojekat.service.studentservice.StudentDirectoryService;

@RestController
@RequestMapping("/api")
public class StudentDirectoryController {

    private final StudentDirectoryService svc;

    public StudentDirectoryController(StudentDirectoryService svc) {
        this.svc = svc;
    }

    @GetMapping("/teacher/{teacherId}/students")
    public List<StudentSearchDTO> teacherScopedSearch(
        @PathVariable Long teacherId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String indexNumber,
        @RequestParam(required = false) Integer enrollmentYear,
        @RequestParam(required = false) Double minAvg,
        @RequestParam(required = false) Double maxAvg
    ) {
        return svc.teacherScopedSearch(teacherId, name, indexNumber, enrollmentYear, minAvg, maxAvg);
    }

    @GetMapping("/students/search")
    public List<StudentSearchDTO> globalSearch(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String indexNumber,
        @RequestParam(required = false) Integer enrollmentYear,
        @RequestParam(required = false) Double minAvg,
        @RequestParam(required = false) Double maxAvg
    ) {
        return svc.globalSearch(name, indexNumber, enrollmentYear, minAvg, maxAvg);
    }

    @GetMapping("/students/{studentId}/profile")
    public StudentProfileDTO profile(@PathVariable Long studentId) {
        return svc.profile(studentId);
    }
}
