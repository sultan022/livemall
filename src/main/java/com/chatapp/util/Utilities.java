package com.chatapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utilities {

		@Autowired
	  MessageSource messageSource;
	

	public String readMultiValueMessage(String name, String lang) {
		
	//	Locale loc
		
		return messageSource.getMessage(name, null, LocaleContextHolder.getLocale());
	}
	
	
}
