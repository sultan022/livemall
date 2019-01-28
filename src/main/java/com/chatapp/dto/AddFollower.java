package com.chatapp.dto;

import javax.validation.constraints.NotNull;

public class AddFollower {

	public AddFollower() {
		super();
	}
	public AddFollower(@NotNull String sourceUserEmail, @NotNull String targetUSerEmail) {
		super();
		this.sourceUserEmail = sourceUserEmail;
		this.targetUserEmail = targetUSerEmail;
	}
	@NotNull
	private String sourceUserEmail;
	@NotNull
	private String targetUserEmail;
	
	public String getSourceUserEmail() {
		return sourceUserEmail;
	}
	public void setSourceUserEmail(String sourceUserEmail) {
		this.sourceUserEmail = sourceUserEmail;
	}
	public String getTargetUSerEmail() {
		return targetUserEmail;
	}
	public void setTargetUSerEmail(String targetUSerEmail) {
		this.targetUserEmail = targetUSerEmail;
	}
	
	
}
