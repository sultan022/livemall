package com.chatapp.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ResponseBody
public class CustomResponse<T> {

	private String message;
	private String messageForUser;
	private HttpStatus responseCode;
	private T data;
	private List<T> arrayData;

	public String getMessage() {
		return message;
	}
	public void setMessage(String customMessage) {

		this.message = customMessage;
	}

	public void setResponseCode(HttpStatus any) {
		this.responseCode = any;
	}

	public HttpStatus getResponseCode() {
		return responseCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	public String getMessageForUser() {
		return messageForUser;
	}
	public void setMessageForUser(String messageForUser) {
		this.messageForUser = messageForUser;
	}
	public List<T> getArrayData() {
		return arrayData;
	}
	public void setArrayData(List<T> arrayData) {
		this.arrayData = arrayData;
	}

}