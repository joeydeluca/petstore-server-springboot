package com.petservice.domain.authentication;

import com.petservice.controllers.dtos.AuthenticationRequestDto;
import com.petservice.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Joe Deluca on 9/28/2016.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private TokenUtils tokenUtils;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenUtils tokenUtils, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Perform the authentication
     * @return Authentication
     */
    @Override
    public Authentication authenticate(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    /**
     *
     * @param authenticationRequestDto
     * @return the token
     */
    @Override
    public String generateToken(AuthenticationRequestDto authenticationRequestDto) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());
        return this.tokenUtils.generateToken(userDetails);
    }

    /**
     *
     * @param authentication
     * @return the role
     */
    @Override
    public String getRole(Authentication authentication) {
        return authentication.getAuthorities().stream().findFirst().get().getAuthority();
    }
}
