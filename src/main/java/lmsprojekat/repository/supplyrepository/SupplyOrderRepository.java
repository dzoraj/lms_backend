package lmsprojekat.repository.supplyrepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import lmsprojekat.model.supply.SupplyOrder;

public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
  List<SupplyOrder> findByFaculty_IdOrderByCreatedAtDesc(Long facultyId);
}
