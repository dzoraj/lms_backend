package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager em;

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
                admin.getJmbg(),
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
        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // Fetch the role
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));

        // Assign role to user if not already present
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }

        // Insert into subclass table only if it exists
        String tableName = roleName.toLowerCase();

        try {
            // Check if table exists
            Number count = (Number) em.createNativeQuery(
                    "SELECT COUNT(*) FROM information_schema.tables " +
                    "WHERE table_schema = DATABASE() AND table_name = :tableName")
                    .setParameter("tableName", tableName)
                    .getSingleResult();

            boolean tableExists = count != null && count.longValue() > 0;

            if (tableExists) {
                // Insert into subclass table only if the ID does not already exist
                em.createNativeQuery(
                        "INSERT INTO " + tableName + " (id) " +
                        "SELECT :userId WHERE NOT EXISTS " +
                        "(SELECT 1 FROM " + tableName + " WHERE id = :userId)")
                        .setParameter("userId", userId)
                        .executeUpdate();
            }
        } catch (jakarta.persistence.PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause.getMessage() != null &&
                cause.getMessage().contains("Table") && cause.getMessage().contains("doesn't exist")) {
                // Table doesn't exist, safe to ignore
            } else {
                throw e; // rethrow other exceptions
            }
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
