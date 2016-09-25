package com.petservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public class AuthenticatedUser  implements Authentication {

    private String name;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated = true;

    AuthenticatedUser(String name, String role) {
        List<GrantedAuthority> authorityCollections = new ArrayList();
        authorityCollections.add(() -> role);
        this.authorities = authorityCollections;
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.name;
    }
}