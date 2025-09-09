package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDO {
  private Long id;
  private String name;
  private String permissionDescription;
}