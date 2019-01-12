package com.chatapp.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException  extends RuntimeException{

	
	static final long serialVersionUID = -2L;


    public CustomException(String message)
    {
        super(message);
    }
}
