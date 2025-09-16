package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;

public interface ReservationServicePort {

  ReservationDO add(ReservationDO reservationDO);
}