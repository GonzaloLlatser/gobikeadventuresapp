package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "reservation_date")
  private LocalDateTime reservationDate;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne (cascade = CascadeType.ALL)
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @ManyToOne
  @JoinColumn(name = "experience_id")
  private Experience experience;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ReservationStatus status;

  public enum ReservationStatus {
    PENDING, CONFIRMED, CANCELLED, COMPLETED
  }
}
