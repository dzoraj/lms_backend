package lmsprojekat.model.users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lmsprojekat.model.forum.UserOnForum;

@Entity

public class Administrator extends RegisteredUser {
	@Column(nullable = true)

	private String accessLevel;

	public Administrator() {
		super();
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Administrator(Long id, String email, String password, List<Role> roles, List<UserOnForum> userOnForums,
			String accessLevel) {
		super(id, email, password, roles, userOnForums);
		this.accessLevel = accessLevel;
	}

}