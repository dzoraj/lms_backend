package lmsprojekat.dto;

public class RoleAssignmentRequest {
    public Long userId;
    public String roleName;
	public RoleAssignmentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleAssignmentRequest(Long userId, String roleName) {
		super();
		this.userId = userId;
		this.roleName = roleName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
