package com.jkh9610.backboard.security;

import java.util.*;

// import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

// 스프링시큐리티 핵심파일!
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // @PreAuthorize 사용설정
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {   // throws Exception 빠지면 오류생김
        // http://localhost:8080/** 로그인하지 않고도 접근할 수 있는 권한을 주겠다 */
        http
        // 다 접근가능(게시판 보기 가능) 글쓰기는 로그인을 해야한다
        .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/**"))
                                        .permitAll())
        // 로그인,회원가입 페이지만 로그인 하지 않고도 접근가능
            // .authorizeHttpRequests((atr) -> atr.requestMatchers(new AntPathRequestMatcher("/member/register"),
            //                                                     new AntPathRequestMatcher("/member/login)")
            // .permitAll())
            // CROS 타서버간 접근 권한
            .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()))
            //  CSRF 위변조 공격을 막는 부분 해제, 특정 URL은 csrf공격 리스트에서 제거
            // .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
            // REST API 전달시 403 Error 발생
            .csrf((csrf) -> csrf.disable())
            // h2-console 페이지가 frameset, frame으로 구성 CORS 와 유사한 옵션추가
            .headers((headers) -> headers
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN  //ignoringRequestsMatchers 영역에 있는 프레임이니까 해제해줘. 라는 의미
                )))
                // 로그인 url을 지정 ~/member/login 로그인 성공하면 루트로 변경
                .formLogin((fl) -> fl.loginPage("/member/login")
                    .defaultSuccessUrl("/"))
                // 로그아웃 처리
                .logout((logout) -> logout.logoutRequestMatcher
                    (new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;

        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return requset -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); //허용할 Origin URL
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder(); // 암호와 빈으로 생성
    }
}
