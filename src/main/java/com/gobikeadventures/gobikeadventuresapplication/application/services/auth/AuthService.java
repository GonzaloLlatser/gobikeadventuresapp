package com.gobikeadventures.gobikeadventuresapplication.application.services.auth;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.AuthServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.AuthRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {

  private final AuthRepositoryPort authRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;

  public AuthService(AuthRepositoryPort authRepositoryPort, PasswordEncoderPort passwordEncoderPort, RoleRepositoryPort roleRepositoryPort, RoleRepositoryPort roleRepositoryPort1) {
    this.authRepositoryPort = authRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;

  }

  @Override
  public AuthDO login(AuthDO authDO) {

    AuthDO user = authRepositoryPort.findByEmail(authDO.getEmail())
      .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoderPort.matches(authDO.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }
    return user;
  }
}