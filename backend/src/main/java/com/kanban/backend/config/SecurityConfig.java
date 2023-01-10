package com.kanban.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    JdbcUserDetailsManager users(DataSource dataSource, PasswordEncoder passwordEncoder) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers(toH2Console());
                    csrf.ignoringRequestMatchers("/auth/register");
                })
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(toH2Console())
                        .permitAll()
                        .requestMatchers("/auth/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .headers(headers -> headers
                        .frameOptions()
                        .sameOrigin()
                )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
