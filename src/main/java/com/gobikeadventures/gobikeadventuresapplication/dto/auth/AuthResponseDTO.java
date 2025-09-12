package com.gobikeadventures.gobikeadventuresapplication.dto.auth;

import com.gobikeadventures.gobikeadventuresapplication.dto.role.RoleResponseDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseDTO {
  private UUID id;
  private String email;
  private String token;
  private RoleResponseDTO role;
}