package lmsprojekat.dto.userdto;

import java.util.List;

public class RegisteredUserDTO extends UserResponseDTO {
    private List<Long> userOnForumIds;

    public RegisteredUserDTO() {}

    public RegisteredUserDTO(Long id, String email, List<String> roleNames, List<Long> userOnForumIds) {
        super(id, email, roleNames);
        this.userOnForumIds = userOnForumIds;
    }

    public List<Long> getUserOnForumIds() {
        return userOnForumIds;
    }

    public void setUserOnForumIds(List<Long> userOnForumIds) {
        this.userOnForumIds = userOnForumIds;
    }
}
