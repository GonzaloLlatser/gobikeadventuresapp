package com.gobikeadventures.gobikeadventuresapplication.dto.user;

import lombok.Data;

@Data
public class RoleResponseDTO {
  private Long id;
  private String name;
  private String permission;
}