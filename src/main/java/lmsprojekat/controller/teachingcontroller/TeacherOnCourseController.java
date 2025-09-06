package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.TeacherOnCourseDTO;
import lmsprojekat.service.teachingservice.TeacherOnCourseService;

@RestController
@RequestMapping("/api/teacherOnCourse")
public class TeacherOnCourseController extends BaseCrudController<TeacherOnCourseDTO, Long> {

    private final TeacherOnCourseService service;

    public TeacherOnCourseController(TeacherOnCourseService service) {
        this.service = service;
    }

    @Override
    protected TeacherOnCourseService getService() {
        return service;
    }
}
