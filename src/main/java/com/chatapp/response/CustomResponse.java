package com.chatapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ResponseBody
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {


    private String message;
    private String resultCode;
    private String exception;
    private String callStatus;
    private T data;


}