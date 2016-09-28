package com.petservice.controllers;

import com.petservice.controllers.dtos.AuthenticationRequestDto;
import com.petservice.controllers.dtos.AuthenticationResponseDto;
import com.petservice.domain.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  private AuthenticationService authenticationService;

  @Autowired
  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<AuthenticationResponseDto> authenticationRequest(
          @RequestBody AuthenticationRequestDto authenticationRequestDto) throws AuthenticationException {

    Authentication authentication = authenticationService.authenticate(authenticationRequestDto);

    String token = authenticationService.generateToken(authenticationRequestDto);

    String role = authenticationService.getRole(authentication);

    AuthenticationResponseDto response = new AuthenticationResponseDto(token, role);

    return ResponseEntity.ok(response);
  }

}
