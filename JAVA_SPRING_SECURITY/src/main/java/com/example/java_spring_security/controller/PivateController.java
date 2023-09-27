package com.example.java_spring_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PivateController {
    @GetMapping("/private")
    public String getPrivateHome(){
        return "views/home";
    }
}
