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

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
    AuthDO authDO = authMapper.toModel(authRequestDTO);
    AuthResponseDTO authResponseDTO = authMapper.toDTO(authServicePort.login(authDO));
    return ResponseEntity.ok(authResponseDTO);
  }
}