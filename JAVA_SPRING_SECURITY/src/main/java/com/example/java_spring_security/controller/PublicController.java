package com.example.java_spring_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @GetMapping
    public String getPublicHome(){

        return "index";
    }
}
