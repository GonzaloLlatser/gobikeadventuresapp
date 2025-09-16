package com.gobikeadventures.gobikeadventuresapplication.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDO {
  private Long id;
  private LocalDateTime date;
  private String status;
  private String transactionId;
}