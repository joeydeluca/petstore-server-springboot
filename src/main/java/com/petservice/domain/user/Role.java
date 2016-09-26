package com.petservice.domain.user;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public enum Role {
    ROLE_GUEST, ROLE_ADMIN;

    public String getRoleName() {
        return this.name().replace("ROLE_", "");
    }
}
