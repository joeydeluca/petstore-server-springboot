package com.petservice.controllers.dtos;

/**
 * Created by joseph.deluca of the House Targaryen, the first of his name. Coder of the Seven Kingdoms of Leonardo and Protector of the Realm, The Unburnt, Mother of Dragons, Breaker of Chains and Khaleesi of the Great Grass Sea.
 * I created this file on 26/09/2016.
 */
public class AuthenticationRequestDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
