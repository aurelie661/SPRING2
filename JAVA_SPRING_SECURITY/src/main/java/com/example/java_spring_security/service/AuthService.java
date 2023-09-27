package com.example.java_spring_security.service;

import com.example.java_spring_security.entities.UserEntity;
import com.example.java_spring_security.models.AuthenticationRequests;
import com.example.java_spring_security.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserEntityRepository userEntityRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public void register(AuthenticationRequests authenticationRequests){
        if(!userEntityRepository.existsByEmail(authenticationRequests.getEmail())){
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(authenticationRequests.getEmail());
            userEntity.setPassword(passwordEncoder.encode(authenticationRequests.getPassword()));

            userEntityRepository.save(userEntity);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequests.getEmail(),
                authenticationRequests.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void authenticate(AuthenticationRequests authenticationRequests){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequests.getEmail(),
                authenticationRequests.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
