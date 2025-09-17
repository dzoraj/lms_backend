package lmsprojekat.dto.supplydto;

import lmsprojekat.model.supply.FacultySupply;
import lmsprojekat.model.supply.SupplyOrder;

public final class SupplyDtoMappers {

  public static FacultySupplyDTO toDto(FacultySupply fs) {
    var dto = new FacultySupplyDTO();
    dto.id = fs.getId();
    dto.facultyId = fs.getFaculty().getId();
    dto.itemName = fs.getItemName();
    dto.quantity = fs.getQuantity();
    dto.updatedAt = fs.getUpdatedAt();
    return dto;
  }

  public static SupplyOrderDTO toDto(SupplyOrder so) {
    var dto = new SupplyOrderDTO();
    dto.id = so.getId();
    dto.facultyId = so.getFaculty().getId();
    dto.itemName = so.getItemName();
    dto.quantity = so.getQuantity();
    dto.status = so.getStatus();
    dto.createdAt = so.getCreatedAt();
    return dto;
  }

  private SupplyDtoMappers() {}
}
