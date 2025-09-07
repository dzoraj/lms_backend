package lmsprojekat.model.users;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lmsprojekat.model.forum.UserOnForum;

@Entity

public class RegisteredUser extends User {

    
    @OneToMany(mappedBy = "registeredUser")
    private List<UserOnForum> userOnForums;
    
    


	public RegisteredUser(Long id,String name,String jmbg, String email, String password, List<Role> roles, List<UserOnForum> userOnForums) {
		super(id,name, jmbg,email, password, roles);
		this.userOnForums = userOnForums;
	}





	public RegisteredUser() {
		super();
		// TODO Auto-generated constructor stub
	}





	public List<UserOnForum> getUserOnForums() {
		return userOnForums;
	}





	public void setUserOnForums(List<UserOnForum> userOnForums) {
		this.userOnForums = userOnForums;
	}


	

}