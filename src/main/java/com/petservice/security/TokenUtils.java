package com.petservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

  private final Logger logger = Logger.getLogger(this.getClass());

  private String secret = "joeyiscooljoeyiscooljoeyiscooljoeyiscooljoeyiscool";

  private int expiration = 1000 * 60 * 60 * 24; // 1 day;

  public String getUsernameFromToken(String token) {
    String username;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration;
    try {
      final Claims claims = this.getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    try {
      claims = Jwts.parser()
        .setSigningKey(this.secret)
        .parseClaimsJws(token)
        .getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  private Date generateCurrentDate() {
    return new Date(System.currentTimeMillis());
  }

  private Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + this.expiration * 1000);
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = this.getExpirationDateFromToken(token);
    return expiration.before(this.generateCurrentDate());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("sub", userDetails.getUsername());
    claims.put("created", this.generateCurrentDate());
    return this.generateToken(claims);
  }

  private String generateToken(Map<String, Object> claims) {
    return Jwts.builder()
      .setClaims(claims)
      .setExpiration(this.generateExpirationDate())
      .signWith(SignatureAlgorithm.HS512, this.secret)
      .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) userDetails;
    final String username = this.getUsernameFromToken(token);
    return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)));
  }

}
