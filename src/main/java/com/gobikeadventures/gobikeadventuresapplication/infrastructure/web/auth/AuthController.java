package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.auth;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.AuthServicePort;
import com.gobikeadventures.gobikeadventuresapplication.dto.auth.AuthRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.auth.AuthResponseDTO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.auth.AuthMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthServicePort authServicePort;
  private final AuthMapper authMapper;

  public AuthController(AuthServicePort authServicePort, AuthMapper authMapper) {
    this.authServicePort = authServicePort;
    this.authMapper = authMapper;
  }

  /**
   * POST /auth/login
   *
   * Authenticates a user with provided credentials.
   * Accepts an AuthRequestDTO containing email and password.
   * Converts the DTO to a domain object, delegates login logic to AuthServicePort,
   * then maps the resulting domain object to an AuthResponseDTO.
   *
   * Returns HTTP 200 with JWT token and user info on success.
   *
   * @param authRequestDTO DTO containing login credentials
   * @return ResponseEntity with AuthResponseDTO containing token and user details
   */
  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
    // Map request DTO to domain object
    AuthDO authDO = authMapper.toModel(authRequestDTO);

    // Delegate login logic to service and map result back to response DTO
    AuthResponseDTO authResponseDTO = authMapper.toDTO(authServicePort.login(authDO));

    // Return HTTP 200 OK with authentication info
    return ResponseEntity.ok(authResponseDTO);
  }
}