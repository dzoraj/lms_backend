package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lmsprojekat.dto.userdto.UserRequestDTO;
import lmsprojekat.dto.userdto.UserResponseDTO;
import lmsprojekat.model.users.Role;
import lmsprojekat.model.users.User;
import lmsprojekat.repository.userrepo.UserRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class UserService extends AbstractCrudService<UserRequestDTO, User, Long> {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;
    
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
	public UserRepository getRepository() {
        return userRepository;
    }

    private UserResponseDTO mapToDTO(User user) {
        List<String> roleNames = user.getRoles().stream()
                                     .map(r -> r.getName())
                                     .collect(Collectors.toList());
        return new UserResponseDTO(user.getId(),user.getName(), user.getJmbg(), user.getEmail(), roleNames);
    }

    public List<UserResponseDTO> searchUsers(String query) {
        String lowerQuery = query.toLowerCase();

        return userRepository.findAll().stream()
            .filter(u -> 
                (u.getName() != null && u.getName().toLowerCase().contains(lowerQuery)) ||
                (u.getEmail() != null && u.getEmail().toLowerCase().contains(lowerQuery)) ||
                (u.getRoles() != null && u.getRoles().stream()
                    .map(Role::getName)             
                    .anyMatch(rn -> rn.toLowerCase().contains(lowerQuery))) 
            )
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }



    @Override
    protected User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setJmbg(dto.getJmbg());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // BCrypt
        user.setRoles(List.of()); // default no roles on creation
        return user;
    }

    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    @Override
    protected UserRequestDTO toDTO(User entity) {
        return new UserRequestDTO(entity.getId(),entity.getName(),entity.getJmbg(),entity.getEmail(), "");
    }

    @Override
    protected void updateEntity(User entity, UserRequestDTO dto) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getJmbg() != null && !dto.getJmbg().isBlank()) {
            entity.setJmbg(dto.getJmbg());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }


    @Transactional
    public UserResponseDTO createUser(UserRequestDTO request) {
        User savedUser = userRepository.save(toEntity(request));

        em.createNativeQuery("INSERT INTO registered_user (id) VALUES (?)")
          .setParameter(1, savedUser.getId())
          .executeUpdate();

        return mapToDTO(savedUser);
    }


    public List<UserResponseDTO> getAllUsers() {
        return getRepository().findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToDTO(user);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User user = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateEntity(user, request);
        return mapToDTO(getRepository().save(user));
    }

    public void deleteUser(Long id) {
        if (!getRepository().existsById(id)) {
            throw new RuntimeException("User not found");
        }
        getRepository().deleteById(id);
    }

}
