package lmsprojekat.controller.userscontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.studentdto.StudentProfileDTO;
import lmsprojekat.dto.teachingdto.UpcomingExamDTO;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.service.dashboardservice.StudentDashboardService;
import lmsprojekat.service.teachingservice.ExamApplicationService;
import lmsprojekat.service.userservice.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends BaseCrudController<StudentDTO, Long> {
    private final ExamApplicationService examApplicationService;
    private final StudentService service;
    private final StudentDashboardService dashboardService;


    public StudentController(StudentService service,StudentDashboardService dashboardService,ExamApplicationService examApplicationService) {
        this.service = service;
        this.dashboardService = dashboardService;
        this.examApplicationService=examApplicationService;
    }

    @Override
    protected StudentService getService() {
        return service;
    }

    @GetMapping("/dashboard/{id}")
    public StudentProfileDTO getDashboard(@PathVariable Long id) {
        return dashboardService.getDashboard(id);
    }
    @GetMapping("/upcoming-exams/{studentId}")
    public List<UpcomingExamDTO> getUpcomingExams(@PathVariable Long studentId) {
        return examApplicationService.getUpcomingExamsForStudent(studentId);
    }
}
