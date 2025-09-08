package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bike")
@Data
public class Bike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BikeStatus status;

  private java.time.LocalDate lastControlDate;

  public enum BikeStatus {
    AVAILABLE, RENTED, MAINTENANCE, RETIRED
  }

}
