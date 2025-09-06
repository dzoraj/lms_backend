package lmsprojekat.service.userservice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.userdto.RoleDTO;
import lmsprojekat.model.users.Role;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.userrepo.RoleRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class RoleService extends AbstractCrudService<RoleDTO, Role, Long> {
    

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected SoftDeleteRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @Override
    protected RoleDTO toDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    @Override
    protected Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId()); 
        role.setName(dto.getName());
        return role;
    }

    @Override
    protected void updateEntity(Role role, RoleDTO dto) {
        role.setName(dto.getName());
    }

    public RoleDTO findByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
        return toDTO(role);
    }

    public RoleDTO createRole(String name) {
        Optional<Role> existing = roleRepository.findByName(name);
        if (existing.isPresent()) {
            throw new RuntimeException("Role already exists: " + name);
        }
        Role role = new Role();
        role.setName(name);
        return toDTO(roleRepository.save(role));
    }
}
