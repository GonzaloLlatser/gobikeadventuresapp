package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthDO {
  private String token;
  private Long id;
  private String email;
  private String password;
  private RoleDO roleDO;
}