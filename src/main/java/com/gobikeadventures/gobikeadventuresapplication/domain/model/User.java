package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
  private UUID id;
  private String password;
  private String email;
}
