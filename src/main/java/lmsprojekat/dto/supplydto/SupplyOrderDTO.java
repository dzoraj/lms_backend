package lmsprojekat.dto.supplydto;

import java.time.LocalDateTime;

public class SupplyOrderDTO {
  public Long id;
  public Long facultyId;
  public String itemName;
  public Integer quantity;
  public String status;          
  public LocalDateTime createdAt;
}
