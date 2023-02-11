package com.alphanah.alphanahbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        String[] anonymousEndpoints = {
                "/register/customer",
                "/register/merchant",
                "/login"
        };

        String[] permitAllEndpoints = {
                "/product",
                "/product/*",
                "/category",
                "/category/*",
                "/pay"
        };

        httpSecurity
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers(anonymousEndpoints).anonymous()
                .requestMatchers(permitAllEndpoints).permitAll()
                .anyRequest().authenticated().and()
                .oauth2ResourceServer().jwt();
        return httpSecurity.build();
    }

}
