package com.gobikeadventures.gobikeadventuresapplication.application.services.auth;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.AuthServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.AuthRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {

  private final AuthRepositoryPort authRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthService(AuthRepositoryPort authRepositoryPort, PasswordEncoderPort passwordEncoderPort, RoleRepositoryPort roleRepositoryPort, RoleRepositoryPort roleRepositoryPort1, JwtTokenProvider jwtTokenProvider) {
    this.authRepositoryPort = authRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public AuthDO login(AuthDO authDO) {

    AuthDO user = authRepositoryPort.findByEmail(authDO.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoderPort.matches(authDO.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    String token = jwtTokenProvider.generateToken(user.getId().toString(), user.getEmail(), user.getRoleDO().getName());

    user.setToken(token);

    return user;
  }
}