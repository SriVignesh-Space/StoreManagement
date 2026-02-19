package com.storemanagement.vinyl.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.AuthRequest;
import com.storemanagement.vinyl.Utils.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth")
    public String postMethodName(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Auth Req reached");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            
            return jwtUtil.generateToken(authRequest.getEmail());
        } catch (Exception e) {
            throw e;
        }
    }
    
}
