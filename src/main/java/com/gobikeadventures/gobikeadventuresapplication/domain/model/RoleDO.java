package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDO {
  private String id;
  private String name;
  private String permission_description;
}