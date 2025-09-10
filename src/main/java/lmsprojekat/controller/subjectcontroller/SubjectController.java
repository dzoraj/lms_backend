package lmsprojekat.controller.subjectcontroller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.service.subjectservice.SubjectService;
import lmsprojekat.service.subjectservice.SubjectQueryService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends BaseCrudController<SubjectDTO, Long> {

    private final SubjectService service;
    private final SubjectQueryService queryService;

    public SubjectController(SubjectService service, SubjectQueryService queryService) {
        this.service = service;
        this.queryService = queryService;
    }

    @Override
    protected SubjectService getService() {
        return service;
    }


    @GetMapping("/{id}/full")
    public ResponseEntity<SubjectDTO> getFull(@PathVariable("id") Long id) {
        return ResponseEntity.ok(queryService.getSubjectFull(id));
    }

    @GetMapping("/program/{programId}/overview")
    public ResponseEntity<Map<String, Object>> getProgramOverview(@PathVariable("programId") Long programId) {
        return ResponseEntity.ok(queryService.getStudyProgramOverview(programId));
    }
}
