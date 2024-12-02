package com.reminder.demo.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/").permitAll();
                    registry.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler)
                        .defaultSuccessUrl("/api/users/", true))
                .formLogin(Customizer.withDefaults())
                .build();

//        http
//                .csrf(csrf -> csrf.disable()) // Отключение CSRF новым способом
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll())  // Разрешить все запросы без авторизации
//                .httpBasic(withDefaults());  // Можно настроить базовую авторизацию, если нужно

    }
}
