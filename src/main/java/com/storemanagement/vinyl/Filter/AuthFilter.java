package com.storemanagement.vinyl.Filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.storemanagement.vinyl.Service.CustomerDetailsService;
import com.storemanagement.vinyl.Utils.JwtUtil;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter{

    JwtUtil jwtUtil;
    CustomerDetailsService customerDetailsService;

    AuthFilter(CustomerDetailsService customerDetailsService, JwtUtil jwtUtil){
        this.customerDetailsService = customerDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // String authHeader = request.getHeader("authorization");
        // System.out.println("Filter reached");
        String token = null, username = null;
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                // System.out.println("Reviewing :"+cookie.getName()+" "+ cookie.getValue());
                if("token".equals(cookie.getName())){
                    token = cookie.getValue();
                    username = jwtUtil.getEmail(token);
                    break;
                }
            }
        }
        // System.out.println(token + "  " + username);
        // using authorization bearer token
        // if(authHeader != null && authHeader.startsWith("Bearer")){
        //     token = authHeader.substring(7);
        //     username = jwtUtil.getEmail(token);
        // }
        
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customerDetailsService.loadUserByUsername(username);
            System.out.println(userDetails.toString());
            if(jwtUtil.validateToken(token, username, userDetails)){
                System.out.println(token + " " + username + " " + jwtUtil.validateToken(token, username, userDetails) + userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
