package com.petservice.domain.user;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public interface UserService {
    User findByUsernameAndPassword(String username, String password);
}
