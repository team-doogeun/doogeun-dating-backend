package com.project.dugeun.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
     String username = authentication.getName();
     String password =authentication.getCredentials().toString();

     // DoogeunUserDetails
        DoogeunUserDetails doogeunUserDetails = new DoogeunUserDetails("kiki123@konkuk.ac.kr","kiki","kiki123","12345");

        // CustomAuthentication 객체 생성하여 반환
        return new CustomAuthentication(doogeunUserDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

}
