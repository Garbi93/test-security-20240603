package com.example.testsecurity20240603.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 비크립트 암호화 메서드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 인증, 인가 작업
        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/","/login","/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                        .anyRequest().authenticated()
                );

        // 로그인 관련 작업
        http
                // formLogin 방식 구현 ------------------------------
//                .formLogin((auth) -> auth.loginPage("/login")
//                        .loginProcessingUrl("/loginProc")
//                        .permitAll()
//                );
                // formLogin 방식 구현 ------------------------------

                // httpBasic 방식 로그인 구현 ------------------------------
                .httpBasic(Customizer.withDefaults());
                // httpBasic 방식 로그인 구현 ------------------------------



        // csrf 필터 관련 작업
        // 주석처리하면 csrf 가 켜지게 된다.
//        http
//                .csrf((auth) -> auth.disable());

        // 다중로그인 설정 관련 작업
        http
                .sessionManagement((auth) -> auth
                        // 하나의 계정에서 최대 동시 접속 가능 수
                        .maximumSessions(1)
                        // 최대 접속 계정 수 초과시 어떤것을 로그아웃 시킬시 결정 -> true 는 새로운 로그인 차단
                        .maxSessionsPreventsLogin(true)
                );

        // 세션 고정 (탈취) 보호
        http
                .sessionManagement((auth) -> auth
                        // 로그인 시 동일 세션에 대한 id 를 변경하는 기능
                        .sessionFixation().changeSessionId()
                );


        return http.build();
    }
}
