package lmsprojekat.repository.supplyrepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import lmsprojekat.model.supply.FacultySupply;

public interface FacultySupplyRepository extends JpaRepository<FacultySupply, Long> {
  List<FacultySupply> findByFaculty_IdOrderByItemNameAsc(Long facultyId);
  Optional<FacultySupply> findByFaculty_IdAndItemNameIgnoreCase(Long facultyId, String itemName);
}
