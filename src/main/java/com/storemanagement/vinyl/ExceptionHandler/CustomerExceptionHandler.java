package com.storemanagement.vinyl.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.storemanagement.vinyl.Exception.CustomerException;

@RestControllerAdvice
public class CustomerExceptionHandler {
    
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<Response> customerHandler(CustomerException ex){
        Response error = new Response(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
