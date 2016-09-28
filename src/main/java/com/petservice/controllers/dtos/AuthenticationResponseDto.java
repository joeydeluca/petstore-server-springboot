package com.petservice.controllers.dtos;

/**
 * Created by Joe Deluca on 9/28/2016.
 */
public class AuthenticationResponseDto {
    private String token;
    private String role;

    public AuthenticationResponseDto() {
        // test
    }

    public AuthenticationResponseDto(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
