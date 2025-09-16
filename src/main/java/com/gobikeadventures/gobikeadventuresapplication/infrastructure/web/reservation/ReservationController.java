package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.reservation;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.ReservationDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.ReservationServicePort;
import com.gobikeadventures.gobikeadventuresapplication.dto.reservation.ReservationRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.reservation.ReservationMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.advice.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

  private final ReservationServicePort reservationServicePort;
  private final ReservationMapper reservationMapper;

  public ReservationController(ReservationServicePort reservationServicePort, ReservationMapper reservationMapper) {
    this.reservationServicePort = reservationServicePort;
    this.reservationMapper = reservationMapper;
  }

  @PostMapping
  public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userIdFromToken = authentication.getName(); // principal = userId (String)

    // Check that the requested ID matches the authenticated user's ID
    if (!userIdFromToken.equals(reservationRequestDTO.getUserId().toString())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponseDTO("Access denied: cannot view other user's data", 403, LocalDateTime.now()));
    }

    ReservationDO reservationDO = reservationMapper.toModel(reservationRequestDTO);

    ReservationDO created = reservationServicePort.add(reservationDO);

    return ResponseEntity.status(HttpStatus.CREATED).body(reservationMapper.toDTO(created));
  }
}