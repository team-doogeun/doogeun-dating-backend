package com.project.dugeun.config;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/User/logout").authenticated()
                .antMatchers("/User/").permitAll()
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
//
//        http.exceptionHandling()
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//        ;
        return http.build();

//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/users/new").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/blindDate", "/groupDate").hasRole("SUPER")
//                .antMatchers("/").hasRole("GENERAL")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/user_access")
//                .failureUrl("/access_denied")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/accessDenied")
//        ;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}