package com.petservice.domain.user;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
