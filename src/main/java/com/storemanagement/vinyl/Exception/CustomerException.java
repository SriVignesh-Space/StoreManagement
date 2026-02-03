package com.storemanagement.vinyl.Exception;

public class CustomerException extends RuntimeException {
    String message;
    public CustomerException(String message){
        super(message);
        this.message = message;
    }
}