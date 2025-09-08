package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

public interface PasswordEncoderPort {
  String encode(String rawPassword);
  boolean matches(String rawPassword, String encodedPassword);
}
