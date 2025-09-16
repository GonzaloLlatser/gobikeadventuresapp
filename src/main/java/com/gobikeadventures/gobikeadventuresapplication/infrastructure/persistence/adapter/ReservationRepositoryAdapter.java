package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.ReservationRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Reservation;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper.ReservationPersistenceMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {

  private final ReservationPersistenceMapper reservationPersistenceMapper;
  private final SpringReservationRepository springReservationRepository;

  public ReservationRepositoryAdapter(ReservationPersistenceMapper reservationPersistenceMapper, SpringReservationRepository springReservationRepository) {
    this.reservationPersistenceMapper = reservationPersistenceMapper;
    this.springReservationRepository = springReservationRepository;
  }

  @Override
  public ReservationDO save(ReservationDO reservationDO) {

    Reservation reservation = reservationPersistenceMapper.toEntity(reservationDO);

    return reservationPersistenceMapper.toDomain(springReservationRepository.save(reservation));
  }
}