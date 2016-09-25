package com.petservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Joe Deluca on 9/24/2016.
 */
public class TokenAuthenticationService {
    private long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 day
    private String secret = "joeyiscooljoeyiscooljoeyiscooljoeyiscooljoeyiscool";
    private String tokenPrefix = "Bearer";
    private String headerString = "Authorization";

    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());

        authentication.getAuthorities().stream().forEach(a -> {
            claims.put("role", a.getAuthority());
        });

        String JWT = Jwts.builder()
                .setSubject(authentication.getName())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        response.addHeader(headerString, tokenPrefix + " " + JWT);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(headerString);
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            if(body.getSubject() != null && body.get("role") != null) {
                return new AuthenticatedUser(body.getSubject(), (String) body.get("role"));
            }
        }
        return null;
    }
}
