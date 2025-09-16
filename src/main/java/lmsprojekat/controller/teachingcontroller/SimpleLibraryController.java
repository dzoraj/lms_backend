package lmsprojekat.controller.teachingcontroller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lmsprojekat.dto.teachingdto.library.LoanDTO;
import lmsprojekat.dto.teachingdto.library.TeachingMaterialInventoryDTO;
import lmsprojekat.model.teaching.MaterialLoan;
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.service.teachingservice.SimpleLibraryService;

@RestController
@RequestMapping("/api/library")
public class SimpleLibraryController {
    private final SimpleLibraryService service;
    public SimpleLibraryController(SimpleLibraryService service) { this.service = service; }

    @PostMapping("/issue")
    public ResponseEntity<LoanDTO> issue(@RequestParam Long teachingMaterialId,
                                         @RequestParam(required = false) Long studentInYearId,
                                         @RequestParam(required = false) Long studentId,
                                         @RequestParam(defaultValue = "1") Integer quantity) {
        MaterialLoan loan;
        if (studentInYearId != null) {
            loan = service.issue(teachingMaterialId, studentInYearId, quantity);
        } else if (studentId != null) {
            loan = service.issueByStudent(teachingMaterialId, studentId, quantity);
        } else {
            throw new IllegalArgumentException("studentInYearId or studentId is required");
        }
        return ResponseEntity.ok(LoanDTO.from(loan));
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long loanId) {
        MaterialLoan loan = service.returnLoan(loanId);
        return ResponseEntity.ok(LoanDTO.from(loan));
    }

    @PostMapping("/inventory/{teachingMaterialId}")
    public ResponseEntity<TeachingMaterialInventoryDTO> setInventory(@PathVariable Long teachingMaterialId,
                                                                     @RequestParam Integer count) {
        TeachingMaterial tm = service.setInventory(teachingMaterialId, count);
        return ResponseEntity.ok(TeachingMaterialInventoryDTO.from(tm));
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<TeachingMaterialInventoryDTO>> inventory() {
        return ResponseEntity.ok(service.inventory());
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanDTO>> loans(@RequestParam(defaultValue = "50") int limit) {
        return ResponseEntity.ok(service.recentLoans(limit));
    }
}
