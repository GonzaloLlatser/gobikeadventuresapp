package com.gobikeadventures.gobikeadventuresapplication.dto.reservation;

import com.gobikeadventures.gobikeadventuresapplication.dto.experience.ExperienceDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.payment.PaymentDTO;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReservationRequestDTO {
  private Long userId;
  private ExperienceDTO experience;
  private LocalDateTime reservationDate;
  private PaymentDTO payment;
  private String status;
}