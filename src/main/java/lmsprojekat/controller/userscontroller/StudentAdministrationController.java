package lmsprojekat.controller.userscontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lmsprojekat.dto.RoleAssignmentRequest;
import lmsprojekat.service.userservice.AdministratorService;

@RestController
@RequestMapping("/api/student-administration")
public class StudentAdministrationController {

    private final AdministratorService adminService;

    public StudentAdministrationController(AdministratorService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/assign-student-role")
    public ResponseEntity<?> assignStudentRole(@RequestBody RoleAssignmentRequest request) {
        try {
            adminService.assignRoleToUser(request.userId, "STUDENT");
            return ResponseEntity.ok("Student role assigned successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove-student-role")
    public ResponseEntity<?> removeStudentRole(@RequestParam Long userId) {
        try {
            adminService.removeRoleFromUser(userId, "STUDENT");
            return ResponseEntity.ok("Student role removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
