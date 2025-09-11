package com.gobikeadventures.gobikeadventuresapplication.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class AuthResponseDTO {
  private UUID id;
  private String email;
  private String token;
}