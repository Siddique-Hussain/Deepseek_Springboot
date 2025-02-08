package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (enable in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER") // Only USER role can access GET /api/users/**
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN") // Only ADMIN role can access POST /api/users/**

                        // Role-based access for /api/admin/**
                        .requestMatchers("/apii/admin/**").hasRole("SIDDIQUE") // Only ADMIN can access /api/admin
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(httpBasic -> {}); // Enable HTTP Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN") // Assign ADMIN role
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER") // Assign USER role
                .build();
        UserDetails custom = User.builder()
                .username("Siddique")
                .password(passwordEncoder().encode("Siddique@1"))
                .roles("SIDDIQUE") // Assign USER role
                .build();

        return new InMemoryUserDetailsManager(admin, user, custom);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}