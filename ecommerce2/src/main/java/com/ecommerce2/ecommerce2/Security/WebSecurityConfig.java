package com.ecommerce2.ecommerce2.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public WebSecurityConfig(JWTFilterToken jwtFilterToken) {
        this.jwtFilterToken = jwtFilterToken;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http
            .csrf(csrf -> csrf.disable());
        http .addFilterBefore(jwtFilterToken, AuthorizationFilter.class);
           http .authorizeHttpRequests(auth -> auth
                   .requestMatchers("/product","/Auth/register","/Auth/login,/Auth/verify").permitAll()
                    .anyRequest().authenticated()

            );return http.build();

  }

}


