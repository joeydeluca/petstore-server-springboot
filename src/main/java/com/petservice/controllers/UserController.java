package com.petservice.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(
            value = "/role",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> findRole() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new NoResultException();
        }

        // Grab the role from the Authentication context
        String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();

        return ResponseEntity.ok(role);
    }

}
