package com.chatapp.dto;

import javax.validation.constraints.NotNull;

public class AddFollower {

	@NotNull
	private String sourceUserEmail;
	@NotNull
	private String targetUSerEmail;
	
	public String getSourceUserEmail() {
		return sourceUserEmail;
	}
	public void setSourceUserEmail(String sourceUserEmail) {
		this.sourceUserEmail = sourceUserEmail;
	}
	public String getTargetUSerEmail() {
		return targetUSerEmail;
	}
	public void setTargetUSerEmail(String targetUSerEmail) {
		this.targetUSerEmail = targetUSerEmail;
	}
	
	
}
