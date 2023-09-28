package com.example.java_spring_security.config;

import com.example.java_spring_security.components.JwtAuthenticationFilter;
import com.example.java_spring_security.components.JwtTokenGenerator;
import com.example.java_spring_security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
     private  final  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
     private  final CustomUserDetailsService customUserDetailsService;
     private final JwtTokenGenerator jwtTokenGenerator;
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
          httpSecurity
                  .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                  .csrf().disable()
                  .exceptionHandling()
                  .authenticationEntryPoint(customAuthenticationEntryPoint)
                  .and()
                  .authorizeHttpRequests()
                  .requestMatchers("/public","/public/**", "/auth/**","/api/public/**").permitAll()
                  .anyRequest().authenticated()
                  .and()
                  .logout((logout) -> logout.logoutUrl("/logout"));
          return httpSecurity.build();
     }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
          return configuration.getAuthenticationManager();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     public JwtAuthenticationFilter jwtAuthenticationFilter(){
          return new JwtAuthenticationFilter(jwtTokenGenerator,customUserDetailsService);
     };
}
