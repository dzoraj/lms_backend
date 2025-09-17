package lmsprojekat.service.teachingservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lmsprojekat.dto.teachingdto.library.LoanDTO;
import lmsprojekat.dto.teachingdto.library.TeachingMaterialInventoryDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.MaterialLoan;
import lmsprojekat.model.teaching.TeachingMaterial;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.teachingrepo.MaterialLoanRepository;
import lmsprojekat.repository.teachingrepo.TeachingMaterialRepository;

@Service
public class SimpleLibraryService {
    private final TeachingMaterialRepository tmRepo;
    private final MaterialLoanRepository loanRepo;
    private final StudentInYearRepository siyRepo;

    public SimpleLibraryService(TeachingMaterialRepository tmRepo, MaterialLoanRepository loanRepo,
                                StudentInYearRepository siyRepo) {
        this.tmRepo = tmRepo;
        this.loanRepo = loanRepo;
        this.siyRepo = siyRepo;
    }

    @Transactional
    public MaterialLoan issue(Long teachingMaterialId, Long studentInYearId, int quantity) {
        TeachingMaterial tm = tmRepo.findById(teachingMaterialId).orElseThrow();
        if (tm.getInventoryCount() < quantity) throw new IllegalStateException("Insufficient stock");
        StudentInYear siy = siyRepo.findById(studentInYearId).orElseThrow();
        tm.setInventoryCount(tm.getInventoryCount() - quantity);
        MaterialLoan loan = new MaterialLoan();
        loan.setTeachingMaterial(tm);
        loan.setStudentInYear(siy);
        loan.setQuantity(quantity);
        loan.setIssuedAt(LocalDateTime.now());
        return loanRepo.save(loan);
    }

    @Transactional
    public MaterialLoan issueByStudent(Long teachingMaterialId, Long studentId, int quantity) {
        TeachingMaterial tm = tmRepo.findById(teachingMaterialId).orElseThrow();
        if (tm.getInventoryCount() < quantity) throw new IllegalStateException("Insufficient stock");
        StudentInYear siy = siyRepo.findTopByStudent_IdOrderByEnrollmentDateDesc(studentId).orElseThrow();
        tm.setInventoryCount(tm.getInventoryCount() - quantity);
        MaterialLoan loan = new MaterialLoan();
        loan.setTeachingMaterial(tm);
        loan.setStudentInYear(siy);
        loan.setQuantity(quantity);
        loan.setIssuedAt(LocalDateTime.now());
        return loanRepo.save(loan);
    }

    @Transactional
    public MaterialLoan returnLoan(Long loanId) {
        MaterialLoan loan = loanRepo.findById(loanId).orElseThrow();
        if (loan.getReturnedAt() != null) return loan;
        TeachingMaterial tm = loan.getTeachingMaterial();
        tm.setInventoryCount(tm.getInventoryCount() + loan.getQuantity());
        loan.setReturnedAt(LocalDateTime.now());
        return loan;
    }

    @Transactional
    public TeachingMaterial setInventory(Long teachingMaterialId, int newCount) {
        TeachingMaterial tm = tmRepo.findById(teachingMaterialId).orElseThrow();
        tm.setInventoryCount(newCount);
        return tm;
    }

    @Transactional(readOnly = true)
    public List<TeachingMaterialInventoryDTO> inventory() {
        return tmRepo.findAll().stream().map(TeachingMaterialInventoryDTO::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LoanDTO> recentLoans(int limit) {
        return loanRepo.findAll(Sort.by(Sort.Direction.DESC, "issuedAt"))
                       .stream()
                       .limit(limit)
                       .map(LoanDTO::from)
                       .collect(Collectors.toList());
    }
}
