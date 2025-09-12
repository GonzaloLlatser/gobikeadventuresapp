package com.gobikeadventures.gobikeadventuresapplication.dto.user;

import com.gobikeadventures.gobikeadventuresapplication.dto.role.RoleResponseDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
  private UUID id;
  private String email;
  private RoleResponseDTO rol;
}
