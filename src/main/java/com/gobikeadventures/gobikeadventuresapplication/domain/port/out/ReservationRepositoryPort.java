package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;

public interface ReservationRepositoryPort {
  ReservationDO save(ReservationDO reservationDO);
}