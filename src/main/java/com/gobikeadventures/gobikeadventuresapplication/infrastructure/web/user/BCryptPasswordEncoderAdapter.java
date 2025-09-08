package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public String encode(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}