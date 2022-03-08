package com.example.mission3_basic.login.config;

import com.example.mission3_basic.login.filter.ExceptionFilter;
import com.example.mission3_basic.login.filter.JwtFilter;
import com.example.mission3_basic.login.jwt.JwtProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@Component
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtProvider jwtProvider;


    public JwtSecurityConfig(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity){
        JwtFilter customFilter = new JwtFilter(jwtProvider);
        ExceptionFilter exceptionFilter = new ExceptionFilter();
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(exceptionFilter, JwtFilter.class);
    }
}
