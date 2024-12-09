package com.example.cnmat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for easier testing
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll() // Allow public access to these endpoints
                        .requestMatchers("/api/users/{id}/profile").authenticated() // Require authentication for profile endpoints
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/api/users/login") // Match the login endpoint
                        .defaultSuccessUrl("/api/users/profile", true) // Optional: Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/users/logout") // Specify logout endpoint
                        .permitAll()
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication for security

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password hashing
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
