package com.storemanagement.vinyl.Filter;


import java.io.IOException;

import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class Logging implements Filter {

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) Request;
        System.out.println(request.getMethod() + " : " + request.getRequestURI() + "Incomming bro");
        chain.doFilter(Request, Response);
        System.out.println(request.getMethod() + " : " + request.getRequestURI() + "Response Sent");
    }
}
