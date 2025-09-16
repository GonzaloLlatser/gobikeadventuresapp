package com.gobikeadventures.gobikeadventuresapplication.dto.payment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
  private LocalDateTime localDateTime;
  private String status;
  private String transactionId;
}