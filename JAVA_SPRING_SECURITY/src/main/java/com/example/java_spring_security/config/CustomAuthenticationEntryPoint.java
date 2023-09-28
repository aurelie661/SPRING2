package com.example.java_spring_security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
       if(request.getRequestURI().startsWith("/private")){
           response.sendError(HttpServletResponse.SC_ACCEPTED, authException.getMessage());
       }else{
           response.sendRedirect(request.getContextPath() + "/auth/authenticate");
       }

    }
}
