package com.chatapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.response.CustomResponse;
import com.chatapp.util.CustomException;
import com.chatapp.util.Utilities;

@ControllerAdvice
@RestController
@SuppressWarnings("rawtypes")
public class GlobalExceptionController {

    @Autowired
    Utilities utilities;


    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public <T> ResponseEntity<?> handleError(HttpServletRequest req, org.springframework.web.bind.MethodArgumentNotValidException ex) {
        ex.printStackTrace();

        CustomResponse<T> customResponse = new CustomResponse<>();

        customResponse.setMessage(utilities.readMultiValueMessage("exception.user.message", req.getHeader("lang")));
        customResponse.setResultCode("500");
        customResponse.setCallStatus("false");
        customResponse.setException("Validation Failed for One or more Parameters");
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public <T> ResponseEntity<?> handleError(HttpServletRequest req, CustomException ex) {
        ex.printStackTrace();
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setException(ex.getMessage());
        customResponse.setMessage(utilities.readMultiValueMessage("exception.user.message", req.getHeader("lang")));
        customResponse.setResultCode("500");
        customResponse.setCallStatus("false");
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<?> handleError(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setException("An error has occured");
        customResponse.setMessage(utilities.readMultiValueMessage("exception.user.message", req.getHeader("lang")));
        customResponse.setResultCode("500");
        customResponse.setCallStatus("false");
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(org.springframework.web.bind.MissingRequestHeaderException.class)
    public <T> ResponseEntity<?> handleError(HttpServletRequest req, org.springframework.web.bind.MissingRequestHeaderException ex) {
        ex.printStackTrace();
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setException(ex.getMessage());
        customResponse.setMessage(utilities.readMultiValueMessage("exception.user.message", req.getHeader("lang")));
        customResponse.setResultCode("500");
        customResponse.setCallStatus("false");
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public <T> ResponseEntity<?> handleError(HttpServletRequest req, RuntimeException ex) {
        ex.printStackTrace();
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setException("An error has occured");
        customResponse.setMessage(utilities.readMultiValueMessage("exception.user.message", req.getHeader("lang")));
        customResponse.setResultCode("500");
        customResponse.setCallStatus("false");
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.BAD_REQUEST);
    }

}
