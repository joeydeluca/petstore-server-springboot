package com.petservice.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public class TokenMissingException extends AuthenticationException {
    public TokenMissingException(String msg) {
        super(msg);
    }
}
