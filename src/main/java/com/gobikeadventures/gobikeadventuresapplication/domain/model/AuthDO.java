package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AuthDO {
  private String token;
  private UUID id;
  private String email;
  private String password;
  private RoleDO roleDO;

  public AuthDO(String token, UUID id, String email) {
  }
}