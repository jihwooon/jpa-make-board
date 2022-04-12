package jpa.imform.service.impl;

import io.jsonwebtoken.Claims;
import jpa.imform.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private JwtUtil jwtUtil;

  public AuthenticationService(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  public String login() {
    return jwtUtil.encode(1L);
  }

  public Long parseToken(String accessToken) {
    Claims claims = jwtUtil.decode(accessToken);
    return claims.get("memberId", Long.class);
  }
}
