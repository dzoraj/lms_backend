package lmsprojekat.controller.userscontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.studentdto.StudentProfileDTO;
import lmsprojekat.dto.userdto.StudentDTO;
import lmsprojekat.service.dashboardservice.StudentDashboardService;
import lmsprojekat.service.userservice.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController extends BaseCrudController<StudentDTO, Long> {

    private final StudentService service;
    private final StudentDashboardService dashboardService;


    public StudentController(StudentService service,StudentDashboardService dashboardService) {
        this.service = service;
        this.dashboardService = dashboardService;

    }

    @Override
    protected StudentService getService() {
        return service;
    }

    @GetMapping("/dashboard/{id}")
    public StudentProfileDTO getDashboard(@PathVariable Long id) {
        return dashboardService.getDashboard(id);
    }
}
