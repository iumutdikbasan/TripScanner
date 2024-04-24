package com.iumutdikbasan.tripSearch.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration

public class DemoSecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/v3/api-docs/**")
                .requestMatchers("configuration/**")
                .requestMatchers("/swagger*/**")
                .requestMatchers("/webjars/**")
                .requestMatchers("/swagger-ui/**");
    }


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, active from app_user where username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, role from app_user where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/flights/searchapi").hasAnyRole("USER", "ADMIN","ROOT")
                        .requestMatchers("/api/flights/**").hasAnyRole("ADMIN","ROOT")
                        .requestMatchers("/api/airports/**").hasAnyRole("ADMIN","ROOT")
                        .requestMatchers("/api/auth/**").hasRole("ROOT")
                        .requestMatchers("/**").hasRole("ROOT").anyRequest().permitAll()
        );


        // Basit bir Authenticator formu kullanmak için.
        http.httpBasic(Customizer.withDefaults());

        // Cross Site Request Forgery (CSRF) iptali.
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}