package com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.reservation;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;
import com.gobikeadventures.gobikeadventuresapplication.dto.reservation.ReservationRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.reservation.ReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

  @Mapping(target = "payment.localDateTime", source = "payment.date")
  ReservationResponseDTO toDTO(ReservationDO reservationDO);

  @Mapping(target = "payment.date", source = "payment.localDateTime")
  ReservationDO toModel(ReservationRequestDTO reservationRequestDTO);
}