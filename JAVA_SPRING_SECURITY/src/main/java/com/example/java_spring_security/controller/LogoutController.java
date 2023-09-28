package com.example.java_spring_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController {

    @PostMapping
    public String logout(){
        return "redirect:/private";
    }
}
