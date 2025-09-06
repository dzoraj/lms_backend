package lmsprojekat.dto.userdto;

import java.util.List;

public class RoleAssignmentDTO {
    private List<String> roles;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public RoleAssignmentDTO(List<String> roles) {
		super();
		this.roles = roles;
	}

	public RoleAssignmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	} 
  
}