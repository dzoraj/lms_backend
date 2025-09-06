package lmsprojekat.controller.subjectcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.CourseAttendanceDTO;
import lmsprojekat.service.subjectservice.CourseAttendanceService;

@RestController
@RequestMapping("/api/courseAttendance")
public class CourseAttendanceController extends BaseCrudController<CourseAttendanceDTO, Long> {

    private final CourseAttendanceService service;

    public CourseAttendanceController(CourseAttendanceService service) {
        this.service = service;
    }

    @Override
    protected CourseAttendanceService getService() {
        return service;
    }
}
