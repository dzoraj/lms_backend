package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.EvaluationTypeDTO;
import lmsprojekat.service.teachingservice.EvaluationTypeService;

@RestController
@RequestMapping("/api/evaluationType")
public class EvaluationTypeController extends BaseCrudController<EvaluationTypeDTO, Long> {

    private final EvaluationTypeService service;

    public EvaluationTypeController(EvaluationTypeService service) {
        this.service = service;
    }

    @Override
    protected EvaluationTypeService getService() {
        return service;
    }
}
