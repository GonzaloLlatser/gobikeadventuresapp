package com.gobikeadventures.gobikeadventuresapplication.infrastructure.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private final Key key;
  private final long validityInMs;

  public JwtTokenProvider(@Value("${jwt.secret}") String base64Secret, @Value("${jwt.expiration-ms}") long validityInMs) {
    byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
    this.validityInMs = validityInMs;
  }

  public String generateToken(String userId, String email) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + validityInMs);

    return Jwts.builder().setSubject(userId).claim("email", email).setIssuedAt(now).setExpiration(expiry).signWith(key, SignatureAlgorithm.HS256).compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException ex) {
      return false;
    }
  }

  public Claims getClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  public String getUserId(String token) {
    return getClaims(token).getSubject();
  }

  public String getRole(String token) {
    Object role = getClaims(token).get("role");
    return role != null ? role.toString() : null;
  }
}
