package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDO {
  private UUID id;
  private String password;
  private String email;
  private RoleDO rol;
}
