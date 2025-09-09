package com.gobikeadventures.gobikeadventuresapplication.dto.user;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
  private UUID id;
  private String email;
  private RoleResponseDTO rol;
}
