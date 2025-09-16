package com.gobikeadventures.gobikeadventuresapplication.dto.auth;

import com.gobikeadventures.gobikeadventuresapplication.dto.role.RoleResponseDTO;
import lombok.Data;


@Data
public class AuthResponseDTO {
  private Long id;
  private String email;
  private String token;
  private RoleResponseDTO role;
}