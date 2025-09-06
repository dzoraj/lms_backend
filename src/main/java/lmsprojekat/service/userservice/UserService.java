package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lmsprojekat.dto.userdto.UserRequestDTO;
import lmsprojekat.dto.userdto.UserResponseDTO;
import lmsprojekat.model.users.User;
import lmsprojekat.repository.userrepo.UserRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class UserService extends AbstractCrudService<UserRequestDTO, User, Long> {
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
	public UserRepository getRepository() {
        return userRepository;
    }

    public UserResponseDTO mapToDTO(User user) {
        List<String> roleNames = user.getRoles().stream()
                                     .map(r -> r.getName())
                                     .collect(Collectors.toList());
        return new UserResponseDTO(user.getId(), user.getEmail(), roleNames);
    }

    @Override
    protected User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // BCrypt
        user.setRoles(List.of()); // default no roles on creation
        return user;
    }



    @Override
    protected UserRequestDTO toDTO(User entity) {
        return new UserRequestDTO(entity.getId(),entity.getEmail(), "");
    }

    @Override
    protected void updateEntity(User entity, UserRequestDTO dto) {
        entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword())); 
        }
    }


    public UserResponseDTO createUser(UserRequestDTO request) {
        User saved = getRepository().save(toEntity(request));
        return mapToDTO(saved);
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
