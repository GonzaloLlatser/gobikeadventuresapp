package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue
  @Column(name = "user_id")
  private UUID id;

  @Column(name = "user_password")
  private String password;

  @Column(name = "user_email")
  private String email;

}
