package com.example.bibliotheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF si nécessaire
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/public/**", "/css/**",
                "/img/**", "/js/**", "/privacy", "/contact", "/req/login", "/req/signup", 
                "cart", "/error","/documents/**").permitAll()  // Autoriser toutes les pages publiques
                .anyRequest().authenticated() // Toutes les autres pages nécessitent une authentification
            )
            .formLogin(form -> form
                .loginPage("/req/login")
                .loginProcessingUrl("/req/login")
                .defaultSuccessUrl("/", true) 
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
