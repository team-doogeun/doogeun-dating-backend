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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/users/logout").permitAll()
//                .antMatchers("/users/login").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET.name(), "/blindDate/{userId}/matches").permitAll()
//                .antMatchers(HttpMethod.POST.name(), "/blindDate/like").permitAll()
//                .antMatchers(HttpMethod.POST.name(), "/group/{userId}/new").permitAll()
                .and()
                .formLogin().disable().csrf().disable().cors()
                .and()
                .exceptionHandling()
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

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/user/logout").authenticated()
//                .antMatchers("/users/login").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET.name(), "/blindDate/{userId}/matches").permitAll()
//                .antMatchers(HttpMethod.POST.name(), "/blindDate/like").permitAll()
//                .and()
//                .formLogin().disable().csrf().disable().cors()
//                .and()
//                .exceptionHandling()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//
//        http.authorizeRequests()
//                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
//                .mvcMatchers("/", "/users/**").permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

