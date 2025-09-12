package lmsprojekat.controller.userscontroller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.service.subjectservice.SyllabusService;
import lmsprojekat.service.userservice.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends BaseCrudController<TeacherDTO, Long> {

    private final TeacherService teacherService;
    private final SyllabusService syllabusService;

    public TeacherController(TeacherService teacherService, SyllabusService syllabusService) {
        this.teacherService = teacherService;
        this.syllabusService = syllabusService;
    }

    @Override
    protected TeacherService getService() {
        return teacherService;
    }


    @GetMapping("/{teacherId}/subjects/{subjectId}/syllabus")
    public List<LearningOutcomeDTO> getSyllabus(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId) {
        return syllabusService.getSyllabusRestricted(teacherId, subjectId);
    }

    @PostMapping("/{teacherId}/subjects/{subjectId}/syllabus")
    public LearningOutcomeDTO saveOutcome(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @RequestBody LearningOutcomeDTO outcome) {
        return syllabusService.saveOutcome(teacherId, subjectId, outcome);
    }

    @DeleteMapping("/{teacherId}/subjects/{subjectId}/syllabus/{outcomeId}")
    public void deleteOutcome(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long outcomeId) {
        syllabusService.deleteOutcome(teacherId, subjectId, outcomeId);
    }
}
