package com.petservice.controllers;

import com.petservice.domain.user.User;
import com.petservice.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> findByUsernameAndPassword(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {

        User user = userService.findByUsernameAndPassword(username, password);
        if(user == null) {
            throw new NoResultException();
        }

        return ResponseEntity.ok(user);
    }

}
