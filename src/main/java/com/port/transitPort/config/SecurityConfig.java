package com.port.transitPort.config;

import com.port.transitPort.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Dostęp publiczny
                        .requestMatchers("/api/**").hasAuthority("ROLE_ADMIN") // Tylko dla Administratora
                        .requestMatchers("/api/warehouses/**").hasAuthority("ROLE_WAREHOUSE") // Tylko dla Magazyniera
                        .requestMatchers("/api/maintenance/**").hasAuthority("ROLE_MAINTAINER") // Tylko dla Konserwatora
                        .requestMatchers("/api/terminals/**").hasAuthority("ROLE_HANDLER") // Tylko dla Przeładunkowego
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
