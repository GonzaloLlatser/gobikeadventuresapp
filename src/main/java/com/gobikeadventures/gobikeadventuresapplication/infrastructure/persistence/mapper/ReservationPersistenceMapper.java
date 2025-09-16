package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationPersistenceMapper {


  @Mapping(target = "experience.id", source = "experience.id")
  @Mapping(target = "experience.bike.id", source = "experience.bikeId")
  Reservation toEntity(ReservationDO reservationDO);

  @Mapping(target = "experience.id", source = "experience.id")
  @Mapping(target = "experience.bikeId", source = "experience.bike.id")
  ReservationDO toDomain(Reservation reservation);
}