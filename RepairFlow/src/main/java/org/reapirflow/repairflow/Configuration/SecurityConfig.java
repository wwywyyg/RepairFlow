package org.reapirflow.repairflow.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API 联调阶段通常关闭 CSRF（之后接 JWT 再细化）
                .csrf(csrf -> csrf.disable())
                // 允许匿名访问的接口（先全放开，或按需精确到具体路径）
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/user/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,  "/user/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH,"/user/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/user/**").permitAll()
                        // 其他全部先放开；等你接入 JWT 再收紧
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
