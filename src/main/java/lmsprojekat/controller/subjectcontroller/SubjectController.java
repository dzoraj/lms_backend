package lmsprojekat.controller.subjectcontroller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.dto.subjectdto.SubjectFullDTO;
import lmsprojekat.service.subjectservice.SubjectQueryService;
import lmsprojekat.service.subjectservice.SubjectService;

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
    public ResponseEntity<SubjectFullDTO> getFull(@PathVariable("id") Long id) {
        return ResponseEntity.ok(queryService.getSubjectFull(id));
    }


    @GetMapping("/program/{programId}/overview")
    public ResponseEntity<Map<String, Object>> getProgramOverview(@PathVariable("programId") Long programId) {
        return ResponseEntity.ok(queryService.getStudyProgramOverview(programId));
    }
}
