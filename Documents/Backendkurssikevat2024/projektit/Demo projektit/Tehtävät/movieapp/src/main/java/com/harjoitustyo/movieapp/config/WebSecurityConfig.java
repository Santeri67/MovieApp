package com.harjoitustyo.movieapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")) // Poistetaan CSRF-tarkistus H2-konsolin poluille
            .headers(headers -> headers
            .frameOptions().disable()) // Salli kehysten käyttö oletusasetuksin
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/home", "/register", "/h2-console/**").permitAll() // Salli pääsy määriteltyihin polkuihin
                .anyRequest().authenticated()) // Kaikki muut polut vaativat autentikoinnin
            .formLogin(form -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)) // Ohjaa kirjautumisen jälkeen "home" -sivulle
                .logout(Customizer.withDefaults()); // Käytä oletusasetuksia uloskirjautumiselle
    
            return http.build();
        }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
