package com.project.dugeun.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/logout").authenticated()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST.name(), "/blind").authenticated()
                .antMatchers(HttpMethod.PUT.name(), "/blind-date/{user_id}").authenticated()
                .antMatchers(HttpMethod.DELETE.name(), "/blind-date/{user_id}").authenticated()
                .and()
                .formLogin().disable().csrf().disable().cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);


        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/users/**").permitAll()
                .anyRequest().authenticated()
        ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}