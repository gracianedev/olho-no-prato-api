package com.gfs.olhonoprato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.net.PasswordAuthentication;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF (uma proteção que não é necessária para nossa API stateless)
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                )
                // Inicia a configuração das regras de autorização para as requisições HTTP
                .authorizeHttpRequests(authz -> authz
                        // PERMITE todas as requisições do tipo POST para "/usuarios"
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        // EXIGE autenticação para todas as outras requisições
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
