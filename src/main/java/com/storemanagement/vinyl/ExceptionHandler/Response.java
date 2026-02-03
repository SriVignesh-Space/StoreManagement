package com.storemanagement.vinyl.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private Map<String, String> response = new HashMap<>();

    public Response(String message){
        response.put("Error", message);
    }
}
