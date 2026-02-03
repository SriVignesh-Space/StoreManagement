package com.storemanagement.vinyl.Exception;

public class VinylException extends RuntimeException {
    String message;
    public VinylException(String message){
        super(message);
        this.message = message;
    }
}