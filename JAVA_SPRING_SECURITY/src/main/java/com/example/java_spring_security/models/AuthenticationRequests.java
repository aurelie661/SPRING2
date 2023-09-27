package com.example.java_spring_security.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequests {
    private String email;
    private  String password;
}
