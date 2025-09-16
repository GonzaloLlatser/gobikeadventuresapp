package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDO {
  private Long userId;
  private PaymentDO payment;
  private ExperienceDO experience;
  private LocalDateTime reservationDate;
  private String status;
  private UserDO user;
}