package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String transactionId;

  @Column(nullable = false)
  private java.time.LocalDateTime date;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentStatus status;

  public enum PaymentStatus {
    PENDING, PAID, FAILED, REFUNDED
  }
}
