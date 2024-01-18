package com.hospital.configs;

import com.hospital.member.service.LoginFailureHandler;
import com.hospital.member.service.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {



    @Bean
    //설정무력화
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        //설정하나도 안하고 무력화한 후에 나중에 하나씩 추가할거임

        //인증 설정 - 로그인
        http.formLogin(f -> {f.loginPage("/member/login")
                                .usernameParameter("userId")
                                .passwordParameter("password")
                                //스프링시큐리티가 대신해주는 기본값
//                              .defaultSuccessUrl("/") // 성공시 메인페이지로 이동
//                              .failureUrl("/member/login?error=true"); //실패시 url

                                //내가 직접 설정
                                .successHandler(new LoginSuccessHandler())
                                .failureHandler(new LoginFailureHandler());

        });

        //로그아웃
        http.logout(c -> {
            c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login"); //로그아웃하면 로그인 페이지로 이동
        });
        return http.build();
    }

    @Bean
    //비밀번호 해시화
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
