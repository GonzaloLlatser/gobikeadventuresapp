package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "experience")
@Data
public class Experience {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private TypeExperience type;

  @ManyToOne
  @JoinColumn(name = "bike_id")
  private Bike bike;

  @Column(nullable = false)
  private java.time.LocalDateTime scheduledDate;
}
