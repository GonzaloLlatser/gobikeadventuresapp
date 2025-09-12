package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.UserServicePort;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserResponseDTO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.user.UserMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.advice.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserServicePort userServicePort;
  private final UserMapper userMapper;

  public UserController(UserServicePort userServicePort, UserMapper userMapper) {
    this.userServicePort = userServicePort;
    this.userMapper = userMapper;
  }

  @GetMapping
  public ResponseEntity<?> getUsers() {
    log.info("ENTRA ACA");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // role validation
    boolean isAdmin = authentication.getAuthorities().stream()
      .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

    if (!isAdmin) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body("Access denied: only admins can list users");
    }
    return ResponseEntity.status(HttpStatus.OK).body(userServicePort.getAll());
  }


  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable UUID id) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userIdFromToken = authentication.getName(); // principal = userId (String)

    if (!userIdFromToken.equals(id.toString())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponseDTO("Access denied: cannot view other user's data", 403, LocalDateTime.now()));
    }

    UserResponseDTO userResponseDTO = userMapper.toDTO(userServicePort.getUserById(id));
    return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {

    UserDO user = userMapper.toModel(userRequestDTO);
    Long roleId = userRequestDTO.getRol();
    UserResponseDTO userResponseDTO = userMapper.toDTO(userServicePort.add(user, roleId));
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
  }
}