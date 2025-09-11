package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.ExamApplicationDTO;
import lmsprojekat.service.AbstractCrudService;
import lmsprojekat.service.teachingservice.ExamApplicationService;

@RestController
@RequestMapping("/api/exam-applications")
public class ExamApplicationController extends BaseCrudController<ExamApplicationDTO, Long> {

    private final ExamApplicationService examApplicationService;

    public ExamApplicationController(ExamApplicationService examApplicationService) {
        this.examApplicationService = examApplicationService;
    }

    @Override
    protected AbstractCrudService<ExamApplicationDTO, ?, Long> getService() {
        return examApplicationService;
    }


}
