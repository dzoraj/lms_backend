package lmsprojekat.controller.userscontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lmsprojekat.controller.BaseCrudController;
import lmsprojekat.dto.RoleAssignmentRequest;
import lmsprojekat.dto.userdto.AdministratorDTO;
import lmsprojekat.dto.userdto.UserResponseDTO;
import lmsprojekat.service.userservice.AdministratorService;
import lmsprojekat.service.userservice.UserService;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController extends BaseCrudController<AdministratorDTO, Long> {


    private final AdministratorService service;
    private final UserService userService; 

    public AdministratorController(AdministratorService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @Override
    protected AdministratorService getService() {
        return service;
    }
    @PostMapping("/assign-role")
    public ResponseEntity<?> assignRoleToUser(@RequestBody RoleAssignmentRequest request) {
    	
        try {
            service.assignRoleToUser(request.userId, request.roleName);
            return ResponseEntity.ok("Role assigned successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/remove-role")
    public ResponseEntity<?> removeRoleFromUser(@RequestParam Long userId, @RequestParam String roleName) {
        try {
            service.removeRoleFromUser(userId, roleName);
            return ResponseEntity.ok("Role removed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/search-users")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String query) {
        List<UserResponseDTO> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }

}
