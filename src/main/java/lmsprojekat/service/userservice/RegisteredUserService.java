package lmsprojekat.service.userservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.userdto.RegisteredUserDTO;
import lmsprojekat.model.forum.UserOnForum;
import lmsprojekat.model.users.RegisteredUser;
import lmsprojekat.repository.userrepo.RegisteredUserRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class RegisteredUserService extends AbstractCrudService<RegisteredUserDTO, RegisteredUser, Long> {
    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    protected RegisteredUserRepository getRepository() {
        return registeredUserRepository;
    }

    @Override
    protected RegisteredUserDTO toDTO(RegisteredUser entity) {
        List<Long> userOnForumIds = entity.getUserOnForums() != null
                ? entity.getUserOnForums().stream()
                    .map(UserOnForum::getId)
                    .collect(Collectors.toList())
                : List.of();

        List<String> roleNames = entity.getRoles() != null
                ? entity.getRoles().stream()
                    .map(role -> role.getName())
                    .collect(Collectors.toList())
                : List.of();

        return new RegisteredUserDTO(
                entity.getId(),
                entity.getEmail(),
                roleNames,
                userOnForumIds
        );
    }

    @Override
    protected RegisteredUser toEntity(RegisteredUserDTO dto) {
        RegisteredUser user = new RegisteredUser();
        user.setId(dto.getId());
        //user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    protected void updateEntity(RegisteredUser entity, RegisteredUserDTO dto) {

        entity.setEmail(dto.getEmail());
    }

    @Override
    public RegisteredUserDTO save(RegisteredUserDTO dto) {
        throw new UnsupportedOperationException("Creation of RegisteredUser is not supported via this service.");
    }
}
