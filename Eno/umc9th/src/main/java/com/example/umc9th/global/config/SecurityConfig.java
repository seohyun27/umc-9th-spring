package com.example.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity      // Spring Security 설정을 활성화
@Configuration
public class SecurityConfig {

    /// 허용할 Url을 따로 빼서 관리
    private final String[] allowUris = {
            // Swagger 허용
            "/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };


    /// SecurityFilterChain을 정의
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests         // HTTP 요청에 대한 접근 제어를 설정
                        .requestMatchers(allowUris)                 // 특정 URL 패턴에 대한 접근 권한을 설정
                        .permitAll()                                // 인증 없이 접근 가능한 경로를 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN")      //  'ADMIN' 역할을 가진 사용자만 접근 가능
                        .anyRequest().authenticated()               // 이 외 모든 요청에 대해 인증을 요구
                )
                .formLogin(form -> form                                     // 폼 기반 로그인에서의 처리
                        .defaultSuccessUrl("/swagger-ui/index.html", true)  // 성공시 /swagger-ui/index.html 으로 리다이렉트
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                   // /logout 경로로 로그아웃을 처리
                        .logoutSuccessUrl("/login?logout")      // 로그아웃 성공 시 /login?logout으로 리다이렉트
                        .permitAll()
                );

        return http.build();
    }

    /// passwordEncoder의 구현체 지정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
