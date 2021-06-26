package com.dojo.logReg.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class loginUser {
	@Email(message="Email must be valid")
    @NotEmpty(message="Email isRequired")
    private String email;
	
	@NotNull(message="Password is required")
    @Size(min=5, max=128,message="Password must be greater than 5 characters")
    private String password;
	
	
	public loginUser(){
		
	}


	public loginUser(@Email(message = "Email must be valid") @NotEmpty(message = "Email isRequired") String email,
			@NotNull(message = "Password is required") @Size(min = 5, max = 128, message = "Password must be greater than 5 characters") String password) {
		super();
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
}
