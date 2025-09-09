package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bike")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BikeStatus status;

  @Enumerated(EnumType.STRING)
  private BikeType type;

  @Column(name = "serial_number")
  private String serialNumber;


  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private String model;

  private String year;

  private String color;

  public enum BikeStatus {
    AVAILABLE, RENTED, MAINTENANCE, RETIRED
  }
  public enum BikeType {
    MOUNTAIN, ROAD, ELECTRIC, URBAN, CHILDREN
  }
}
