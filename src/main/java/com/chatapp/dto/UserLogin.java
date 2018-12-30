package com.chatapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserLogin {

	@Email
	@NotNull(message="username required")
	private String username;
	
	@NotNull(message="password required")
	private String password;
	
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
}
