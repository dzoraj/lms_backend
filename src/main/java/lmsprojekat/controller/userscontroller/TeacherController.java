package lmsprojekat.controller.userscontroller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.dto.teachingdto.ExamApplicationDTO;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.service.subjectservice.SyllabusService;
import lmsprojekat.service.teachingservice.EvaluationAttemptService;
import lmsprojekat.service.teachingservice.ExamApplicationService;
import lmsprojekat.service.userservice.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends BaseCrudController<TeacherDTO, Long> {

    private final TeacherService teacherService;
    private final SyllabusService syllabusService;
    private final EvaluationAttemptService evaluationAttemptService;
    private final ExamApplicationService examApplicationService;
    public TeacherController(
            TeacherService teacherService,
            SyllabusService syllabusService,
            EvaluationAttemptService evaluationAttemptService,ExamApplicationService examApplicationService
    ) {
        this.teacherService = teacherService;
        this.syllabusService = syllabusService;
        this.evaluationAttemptService = evaluationAttemptService;
        this.examApplicationService = examApplicationService;
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

    @PostMapping("/{teacherId}/subjects/{subjectId}/sessions/{sessionId}/outcomes")
    public TeachingSessionDTO assignOutcomesToSession(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long sessionId,
            @RequestBody List<Long> outcomeIds) {
        return syllabusService.assignOutcomesToSession(teacherId, subjectId, sessionId, outcomeIds);
    }

    @GetMapping("/{teacherId}/subjects/{subjectId}/sessions/{sessionId}/outcomes")
    public List<LearningOutcomeDTO> getOutcomesForSession(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long sessionId) {
        return syllabusService.getOutcomesForSession(teacherId, subjectId, sessionId);
    }

    @DeleteMapping("/{teacherId}/subjects/{subjectId}/sessions/{sessionId}/outcomes/{outcomeId}")
    public TeachingSessionDTO removeOutcomeFromSession(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long sessionId,
            @PathVariable Long outcomeId) {
        return syllabusService.removeOutcomeFromSession(teacherId, subjectId, sessionId, outcomeId);
    }

    @PostMapping("/{teacherId}/examApplication/{applicationId}/grade")
    public EvaluationAttemptDTO enterGrade(
            @PathVariable Long teacherId,
            @PathVariable Long applicationId,
            @RequestParam int points,
            @RequestParam(required = false) String note
    ) {
        return evaluationAttemptService.enterGrade(teacherId, applicationId, points, note);
    }
    @GetMapping("/{teacherId}/subjects/{subjectId}/examApplications")
    public List<ExamApplicationDTO> getExamApplications(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId
    ) {
        return examApplicationService.findByTeacherAndSubject(teacherId, subjectId);
    }

}
