package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lmsprojekat.dto.userdto.AdministratorDTO;
import lmsprojekat.model.users.Administrator;
import lmsprojekat.model.users.Role;
import lmsprojekat.model.users.User;
import lmsprojekat.repository.userrepo.AdministratorRepository;
import lmsprojekat.repository.userrepo.RoleRepository;
import lmsprojekat.repository.userrepo.UserRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class AdministratorService extends AbstractCrudService<AdministratorDTO, Administrator, Long> {
	
    private final AdministratorRepository administratorRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdministratorService(AdministratorRepository administratorRepository,
                                UserRepository userRepository,
                                RoleRepository roleRepository) {
        this.administratorRepository = administratorRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    protected AdministratorRepository getRepository() {
        return administratorRepository;
    }

    @Override
    protected AdministratorDTO toDTO(Administrator admin) {
        List<String> roleNames = admin.getRoles() != null
                ? admin.getRoles().stream().map(Role::getName).collect(Collectors.toList())
                : List.of();

        List<Long> userOnForumIds = List.of();

        return new AdministratorDTO(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                roleNames,
                userOnForumIds,
                admin.getAccessLevel()
        );
    }

    @Override
    protected Administrator toEntity(AdministratorDTO dto) {
        Administrator admin = new Administrator();
        admin.setId(dto.getId());
        admin.setEmail(dto.getEmail());
        admin.setAccessLevel(dto.getAccessLevel());
        return admin;
    }

    @Override
    protected void updateEntity(Administrator admin, AdministratorDTO dto) {
        admin.setAccessLevel(dto.getAccessLevel());
    }

    @Override
    public AdministratorDTO save(AdministratorDTO dto) {
        throw new UnsupportedOperationException("Administrator creation not supported via AdministratorService.");
    }


    @Transactional
    public void assignRoleToUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }


    @Transactional
    public void removeRoleFromUser(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        if (!user.getRoles().contains(role)) {
            throw new IllegalArgumentException("User does not have this role");
        }

        user.getRoles().remove(role);

        if (role.getUsers() != null) {
            role.getUsers().remove(user);
        }

        userRepository.save(user);
        roleRepository.save(role);
    }

}
