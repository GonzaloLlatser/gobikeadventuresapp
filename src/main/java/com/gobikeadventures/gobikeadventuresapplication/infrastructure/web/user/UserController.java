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

  /**
   * GET /users
   *
   * Retrieves a list of all users.
   * Access is restricted to users with the ADMIN role.
   *
   * The method validates the role of the authenticated user from the SecurityContext.
   * Returns HTTP 403 if the user does not have the ADMIN role.
   *
   * @return ResponseEntity containing the list of users or an error message.
   */
  @GetMapping
  public ResponseEntity<?> getUsers() {
    // Fetch all users from the service
    return ResponseEntity.status(HttpStatus.OK).body(userServicePort.getAll());
  }

  /**
   * GET /users/{id}
   *
   * Retrieves a single user by its ID.
   * Access is restricted so that users can only view their own data.
   *
   * If the authenticated user's ID does not match the requested ID,
   * returns HTTP 403 with a structured error response.
   *
   * @param id UUID of the user to retrieve
   * @return ResponseEntity containing the user data or an error message
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userIdFromToken = authentication.getName(); // principal = userId (String)

    // Check that the requested ID matches the authenticated user's ID
    if (!userIdFromToken.equals(id.toString())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorResponseDTO("Access denied: cannot view other user's data", 403, LocalDateTime.now()));
    }

    // Map domain object to DTO before returning
    UserResponseDTO userResponseDTO = userMapper.toDTO(userServicePort.getUserById(id));
    return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
  }

  /**
   * POST /users
   *
   * Creates a new user with the provided data.
   * Accepts a UserRequestDTO containing user details and role ID.
   * Converts the DTO to a domain object, calls the service to persist,
   * then maps the resulting domain object to a UserResponseDTO for the response.
   *
   * @param userRequestDTO Data transfer object containing user creation info
   * @return ResponseEntity containing the created user and HTTP 201 status
   */
  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {

    // Map incoming DTO to domain object
    UserDO user = userMapper.toModel(userRequestDTO);

    Long roleId = userRequestDTO.getRol();

    // Call service to create user and map result back to DTO
    UserResponseDTO userResponseDTO = userMapper.toDTO(userServicePort.add(user, roleId));

    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
  }
}