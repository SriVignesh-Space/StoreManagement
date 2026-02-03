package com.storemanagement.vinyl.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.storemanagement.vinyl.Exception.VinylException;

@RestControllerAdvice
public class VinylExceptionHandler {
    
    @ExceptionHandler(VinylException.class)
    public ResponseEntity<Response> vinylHandler(VinylException ex){
        Response error = new Response(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
