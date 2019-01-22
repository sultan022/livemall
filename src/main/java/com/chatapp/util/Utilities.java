package com.chatapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Utilities {

    @Autowired
    MessageSource messageSource;


    public String readMultiValueMessage(String name, String lang) {


        return messageSource.getMessage(name, null, LocaleContextHolder.getLocale());
    }

}
