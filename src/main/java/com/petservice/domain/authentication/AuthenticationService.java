package com.petservice.domain.authentication;

import com.petservice.controllers.dtos.AuthenticationRequestDto;
import org.springframework.security.core.Authentication;

/**
 * Created by Joe Deluca on 9/28/2016.
 */
public interface AuthenticationService {
    Authentication authenticate(AuthenticationRequestDto authenticationRequestDto);

    String generateToken(AuthenticationRequestDto authenticationRequestDto);

    String getRole(Authentication authentication);
}
