package com.project.dugeun.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

public class CustomAuthentication implements Authentication {
    private DoogeunUserDetails doogeunUserDetails;

    public CustomAuthentication(DoogeunUserDetails doogeunUserDetails)
    {
        this.doogeunUserDetails = doogeunUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Principal getPrincipal(){
        return () -> doogeunUserDetails.getUserId(); // getUserId()를 통해 userId 반환
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
