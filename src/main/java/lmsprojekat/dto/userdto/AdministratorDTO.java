package lmsprojekat.dto.userdto;

import java.util.List;

public class AdministratorDTO extends RegisteredUserDTO {
    private String accessLevel;

    public AdministratorDTO() {}

    public AdministratorDTO(Long id, String name,String email, List<String> roleNames, List<Long> userOnForumIds, String accessLevel) {
        super(id,name, email, roleNames, userOnForumIds);
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
