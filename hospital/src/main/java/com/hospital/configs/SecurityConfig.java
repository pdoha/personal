package com.hospital.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    //설정무력화
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        //설정하나도 안하고 무력화한 후에 나중에 하나씩 추가할거임
        return http.build();
    }
}
