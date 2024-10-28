package com.ludo.Ludo.configuration;

import com.ludo.Ludo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public UserDetailsService configure(){
        return customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/createGuest", "/api/signup").permitAll())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().hasAnyRole("USER", "GUEST"))
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
