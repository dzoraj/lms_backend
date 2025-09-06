package lmsprojekat.controller.studentcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.studentdto.StudyProgramDTO;
import lmsprojekat.service.studentservice.StudyProgramService;

@RestController
@RequestMapping("/api/studyProgram")
public class StudyProgramController extends BaseCrudController<StudyProgramDTO, Long> {

    private final StudyProgramService service;

    public StudyProgramController(StudyProgramService service) {
        this.service = service;
    }

    @Override
    protected StudyProgramService getService() {
        return service;
    }
    @GetMapping("/by-faculty/{facultyId}")
    public ResponseEntity<List<StudyProgramDTO>> getByFacultyId(@PathVariable Long facultyId) {
        List<StudyProgramDTO> dtoList = service.getByFacultyId(facultyId);
        return ResponseEntity.ok(dtoList);
    }


}
