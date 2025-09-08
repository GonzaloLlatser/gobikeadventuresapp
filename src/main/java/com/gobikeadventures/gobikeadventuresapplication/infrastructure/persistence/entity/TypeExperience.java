package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type_experience")
@Data
public class TypeExperience {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ExperienceType name;

  @ManyToOne
  @JoinColumn(name = "cost_id", nullable = false)
  private Cost cost;

  public enum ExperienceType {
    RENTAL, TOUR
  }
}
