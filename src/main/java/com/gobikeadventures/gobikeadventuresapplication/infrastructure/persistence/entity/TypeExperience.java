package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_experience")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeExperience {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
