package com.gobikeadventures.gobikeadventuresapplication.application.services.reservation;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.ReservationServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.ReservationRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements ReservationServicePort {

  private final ReservationRepositoryPort reservationRepositoryPort;
  private final UserRepositoryPort userRepositoryPort;

  public ReservationService(ReservationRepositoryPort reservationRepositoryPort, UserRepositoryPort userRepositoryPort) {
    this.reservationRepositoryPort = reservationRepositoryPort;
    this.userRepositoryPort = userRepositoryPort;
  }

  @Override
  public ReservationDO add(ReservationDO reservationDO) {

    UserDO user = userRepositoryPort.getUserById(reservationDO.getUserId());

    reservationDO.setUser(user);

    return reservationRepositoryPort.save(reservationDO);
  }

  @Override
  public ReservationDO findById(String reservationId) {
    return reservationRepositoryPort.findById(reservationId);
  }
}