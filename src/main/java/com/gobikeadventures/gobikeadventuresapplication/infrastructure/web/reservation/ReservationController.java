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
import org.springframework.web.bind.annotation.*;

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

  /**
   * Creates a new reservation for the authenticated user.
   *
   * <p>This endpoint expects a {@link ReservationRequestDTO} in the request body.
   * The user ID contained in the request must match the user ID from the JWT authentication token.
   * Otherwise, the request will be rejected with a {@code 403 FORBIDDEN}.</p>
   *
   * @param reservationRequestDTO the reservation details sent by the client
   * @return {@link ResponseEntity} with:
   *         <ul>
   *           <li>{@code 201 CREATED} and the created reservation if successful</li>
   *           <li>{@code 403 FORBIDDEN} if the user tries to create a reservation for another user</li>
   *         </ul>
   */
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

  /**
   * Retrieves an existing reservation by its ID.
   *
   * <p>If the reservation exists, it is returned as a DTO. If it does not exist,
   * the service layer should throw an exception that is handled by the global {@code @ControllerAdvice}.</p>
   *
   * @param reservationId the ID of the reservation to retrieve
   * @return {@link ResponseEntity} with:
   *         <ul>
   *           <li>{@code 200 OK} and the reservation data if found</li>
   *           <li>{@code 404 NOT FOUND} if the reservation does not exist</li>
   *         </ul>
   */
  @GetMapping("/{reservationId}")
  public ResponseEntity<?> getReservation(@PathVariable String reservationId) {
    ReservationDO reservationDO = reservationServicePort.findById(reservationId);

    return ResponseEntity.status(HttpStatus.OK).body(reservationMapper.toDTO(reservationDO));
  }
}