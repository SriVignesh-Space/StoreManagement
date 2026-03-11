package com.storemanagement.vinyl.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.storemanagement.vinyl.Model.AuthRequest;
import com.storemanagement.vinyl.Utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/auth/")
    public ResponseEntity<?> postMethodName(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        try {
            System.out.println("Auth Req reached");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

            String token= jwtUtil.generateToken(authRequest.getEmail());
            
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60);
            cookie.setSecure(false); // change for production
            cookie.setPath("/");

            response.addCookie(cookie);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            throw e;
        }
    }
    
}
