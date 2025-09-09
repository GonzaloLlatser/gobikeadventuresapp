package com.gobikeadventures.gobikeadventuresapplication.dto.user;

import lombok.Data;

@Data
public class UserRequestDTO {
  private String email;
  private String password;
  private Long rol;
}