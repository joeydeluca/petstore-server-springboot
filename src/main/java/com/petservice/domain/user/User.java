package com.petservice.domain.user;

import com.petservice.domain.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
@Entity
public class User extends DomainEntity {
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Role role;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
