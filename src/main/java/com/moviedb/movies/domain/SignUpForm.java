package com.moviedb.movies.domain;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class SignUpForm {
    @NotEmpty
    @Size(min=5, max=30) //Specifies a range for the user name
    private String username = "";

    @NotEmpty
    @Size(min=7, max=30) //Specifies a range for the password
    private String password = "";

    @NotEmpty
    @Size(min=7, max=30) ////Specifies a range for verifying the password
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER"; //Specifies the default role for a user as USER, ADMIN role is hard-coded to a specific user
	
    //Basic user data getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}