package com.example.java_spring_security.controller;

import com.example.java_spring_security.models.AuthenticationRequests;
import com.example.java_spring_security.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @GetMapping("register")
    public  String getRegisterForm(Model model){
        model.addAttribute("mode", "register");
        model.addAttribute("formValues", AuthenticationRequests.builder().build());

        return "auth/register";
    }

    @PostMapping("register")
    public String postRegisterHandler(AuthenticationRequests authenticationRequests, HttpServletRequest httpServletRequest){
        authService.register(authenticationRequests);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        HttpSession httpSession = httpServletRequest.getSession();

        httpSession.setAttribute("SPRING_SECURITY_CONTEXT",securityContext);

        return "redirect:/private";
    }
}
