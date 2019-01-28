package com.chatapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Locale;

@Component
public class Utilities {

    @Autowired
    MessageSource messageSource;


    public String readMultiValueMessage(String name, String lang) {


        return messageSource.getMessage(name, null, LocaleContextHolder.getLocale());
    }

    
    public Double setPrecision(Double number){
    	
    	 DecimalFormat df2 = new DecimalFormat(".##");
    	 return Double.valueOf(df2.format(number));
    }
    
}
