package lmsprojekat.controller.supplycontroller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.dto.supplydto.*;
import lmsprojekat.dto.supplydto.SupplyDtoMappers;
import lmsprojekat.model.supply.FacultySupply;
import lmsprojekat.model.supply.SupplyOrder;
import lmsprojekat.repository.supplyrepository.FacultySupplyRepository;
import lmsprojekat.repository.supplyrepository.SupplyOrderRepository;
import lmsprojekat.repository.universityrepo.FacultyRepository;

@RestController
@RequestMapping("/api/supply-orders")
public class SupplyOrderController {

  private final SupplyOrderRepository orders;
  private final FacultySupplyRepository stock;
  private final FacultyRepository faculties;

  private static final Set<String> ALLOWED = Set.of("PLACED","APPROVED","DENIED","RECEIVED");

  public SupplyOrderController(SupplyOrderRepository orders,
                               FacultySupplyRepository stock,
                               FacultyRepository faculties) {
    this.orders = orders; this.stock = stock; this.faculties = faculties;
  }

  @GetMapping
  public List<SupplyOrderDTO> list(@RequestParam Long facultyId) {
    return orders.findByFaculty_IdOrderByCreatedAtDesc(facultyId)
                 .stream().map(SupplyDtoMappers::toDto).collect(Collectors.toList());
  }

  @PostMapping
  @Transactional
  public ResponseEntity<SupplyOrderDTO> create(@RequestBody SupplyOrderCreateDTO dto) {
    if (dto.facultyId == null || dto.itemName == null || dto.itemName.isBlank()
        || dto.quantity == null || dto.quantity <= 0) return ResponseEntity.badRequest().build();

    var fac = faculties.findById(dto.facultyId).orElse(null);
    if (fac == null) return ResponseEntity.badRequest().build();

    var so = new SupplyOrder();
    so.setFaculty(fac);
    so.setItemName(dto.itemName.trim());
    so.setQuantity(dto.quantity);
    so.setStatus("PLACED");
    return ResponseEntity.ok(SupplyDtoMappers.toDto(orders.save(so)));
  }

  @PatchMapping("/{id}/status")
  @Transactional
  public ResponseEntity<SupplyOrderDTO> setStatus(@PathVariable Long id, @RequestBody SupplyOrderStatusDTO body) {
    if (body.status == null || !ALLOWED.contains(body.status)) return ResponseEntity.badRequest().build();

    return orders.findById(id).map(order -> {
      order.setStatus(body.status);

      if ("RECEIVED".equals(body.status)) {
        var fs = stock.findByFaculty_IdAndItemNameIgnoreCase(order.getFaculty().getId(), order.getItemName()).orElse(null);
        if (fs == null) {
          var newFs = new FacultySupply();
          newFs.setFaculty(order.getFaculty());
          newFs.setItemName(order.getItemName());
          newFs.setQuantity(order.getQuantity());
          stock.save(newFs);
        } else {
          fs.setQuantity(fs.getQuantity() + order.getQuantity());
          stock.save(fs);
        }
      }
      return ResponseEntity.ok(SupplyDtoMappers.toDto(orders.save(order)));
    }).orElse(ResponseEntity.notFound().build());
  }
}
