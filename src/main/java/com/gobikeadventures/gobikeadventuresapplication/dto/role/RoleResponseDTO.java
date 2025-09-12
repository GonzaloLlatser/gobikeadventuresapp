package com.gobikeadventures.gobikeadventuresapplication.dto.role;

import lombok.Data;

@Data
public class RoleResponseDTO {
  private Long id;
  private String name;
  private String permissionDescription;
}