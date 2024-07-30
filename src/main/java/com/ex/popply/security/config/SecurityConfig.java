package com.ex.popply.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ex.popply.security.principal.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PrincipalOauth2UserService oauth2UserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .formLogin(formLogin -> formLogin.disable()
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                )
                .httpBasic(httpBasic -> httpBasic.disable())
//                .apply(new MyCustomDsl()) // 커스텀 필터 등록
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/member/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService)
                        )
                )
                .build();
    }
}
