package lmsprojekat.controller.supplycontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.dto.supplydto.*;
import lmsprojekat.dto.supplydto.SupplyDtoMappers;
import lmsprojekat.model.supply.FacultySupply;
import lmsprojekat.repository.supplyrepository.FacultySupplyRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;

@RestController
@RequestMapping("/api/faculty-supplies")
public class FacultySupplyController {

  private final FacultySupplyRepository repo;
  private final FacultyRepository facultyRepo;

  public FacultySupplyController(FacultySupplyRepository repo, FacultyRepository facultyRepo) {
    this.repo = repo; this.facultyRepo = facultyRepo;
  }

  @GetMapping
  public List<FacultySupplyDTO> list(@RequestParam Long facultyId) {
    return repo.findByFaculty_IdOrderByItemNameAsc(facultyId)
               .stream().map(SupplyDtoMappers::toDto).collect(Collectors.toList());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<FacultySupplyDTO> upsert(@RequestBody FacultySupplyUpsertDTO dto) {
    if (dto.facultyId == null || dto.itemName == null || dto.itemName.isBlank()
        || dto.quantity == null || dto.quantity <= 0) return ResponseEntity.badRequest().build();

    var fac = facultyRepo.findById(dto.facultyId).orElse(null);
    if (fac == null) return ResponseEntity.badRequest().build();

    var existing = repo.findByFaculty_IdAndItemNameIgnoreCase(dto.facultyId, dto.itemName).orElse(null);
    if (existing == null) {
      var fs = new FacultySupply();
      fs.setFaculty(fac);
      fs.setItemName(dto.itemName.trim());
      fs.setQuantity(dto.quantity);
      return ResponseEntity.ok(SupplyDtoMappers.toDto(repo.save(fs)));
    } else {
      existing.setQuantity(existing.getQuantity() + dto.quantity);
      return ResponseEntity.ok(SupplyDtoMappers.toDto(repo.save(existing)));
    }
  }

  @PatchMapping("/{id}/add")
  @Transactional
  public ResponseEntity<FacultySupplyDTO> add(@PathVariable Long id, @RequestBody FacultySupplyAddDTO dto) {
    if (dto.addQuantity == null || dto.addQuantity <= 0) return ResponseEntity.badRequest().build();
    return repo.findById(id)
      .map(fs -> { fs.setQuantity(fs.getQuantity() + dto.addQuantity);
                   return ResponseEntity.ok(SupplyDtoMappers.toDto(repo.save(fs))); })
      .orElse(ResponseEntity.notFound().build());
  }
}
