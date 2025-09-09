package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "experience")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
