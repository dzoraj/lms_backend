package lmsprojekat.controller.studentcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.studentdto.StudentInYearDTO;
import lmsprojekat.service.studentservice.StudentInYearService;

@RestController
@RequestMapping("/api/studentInYear")
public class StudentInYearController extends BaseCrudController<StudentInYearDTO, Long> {

    private final StudentInYearService service;

    public StudentInYearController(StudentInYearService service) {
        this.service = service;
    }

    @Override
    protected StudentInYearService getService() {
        return service;
    }
}
