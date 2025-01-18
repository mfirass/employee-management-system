package dev.mfirass.employee_management_system.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests()
                .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 console
                .anyRequest().authenticated()  // Require authentication for other endpoints
                .and()
                .httpBasic();  // Enable Basic Authentication

        http.headers().frameOptions().disable();  // Allow frames for H2 console
        return http.build();
    }
}
