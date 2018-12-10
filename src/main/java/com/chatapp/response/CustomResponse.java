package com.chatapp.response;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ResponseBody
public class CustomResponse {

	
	private String customMessage;

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	
}
