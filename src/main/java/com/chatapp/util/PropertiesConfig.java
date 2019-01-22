package com.chatapp.util;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@Data
public class PropertiesConfig {

//    @Value(value = "${main_path}")
//    private String mainPath;
}
