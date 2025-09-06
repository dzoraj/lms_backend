package lmsprojekat.controller.teachingcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.teachingdto.EvaluationInstrumentDTO;
import lmsprojekat.service.teachingservice.EvaluationInstrumentService;

@RestController
@RequestMapping("/api/evaluationInstrument")
public class EvaluationInstrumentController extends BaseCrudController<EvaluationInstrumentDTO, Long> {

    private final EvaluationInstrumentService service;

    public EvaluationInstrumentController(EvaluationInstrumentService service) {
        this.service = service;
    }

    @Override
    protected EvaluationInstrumentService getService() {
        return service;
    }
}
