package com.example.java_spring_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/private")
public class PivateController {
    @GetMapping
    public String getPrivateHome(){
        return "views/home";
    }
}
