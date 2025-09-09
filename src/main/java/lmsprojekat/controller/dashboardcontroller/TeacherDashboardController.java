package lmsprojekat.controller.dashboardcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.dto.userdto.TeacherDTO;
import lmsprojekat.service.dashboardservice.TeacherDashboardService;
import lmsprojekat.service.teachingservice.EvaluationInstrumentService;
import lmsprojekat.service.userservice.TeacherService;

@RestController
@RequestMapping("/api/teacher-dashboard")
public class TeacherDashboardController {

    private final TeacherDashboardService dashboardService;
    private final TeacherService teacherService;
    private final EvaluationInstrumentService evaluationInstrumentService;


    public TeacherDashboardController(TeacherDashboardService dashboardService, TeacherService teacherService,EvaluationInstrumentService evaluationInstrumentService) {
        this.dashboardService = dashboardService;
        this.teacherService = teacherService;
        this.evaluationInstrumentService = evaluationInstrumentService;
    }

    @GetMapping("/{teacherId}")
    public Map<String, Object> getDashboard(@PathVariable Long teacherId) {
        TeacherDTO teacher = teacherService.findById(teacherId);

        List<SubjectDTO> subjects = dashboardService.getSubjectsForTeacher(teacherId);

        Map<String, Object> response = new HashMap<>();
        response.put("id", teacher.getId());
        response.put("name", teacher.getName());
        response.put("jmbg", teacher.getJmbg());
        response.put("email", teacher.getEmail());
        response.put("biography", teacher.getBiography());
        response.put("subjects", subjects);

        return response;
    }
    public List<EvaluationInstrumentDTO> getEvaluationInstrumentsForTeacher(Long teacherId) {
        return evaluationInstrumentService.getRepository()
                .findById(teacherId)
                .stream()
                .map(evaluationInstrumentService::toDTO)
                .collect(Collectors.toList());
    }
}
