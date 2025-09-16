package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Data;

@Data
public class ExperienceDO {
  private Long id;
  private Long bikeId;
  private String bikeSerialNumber;
}