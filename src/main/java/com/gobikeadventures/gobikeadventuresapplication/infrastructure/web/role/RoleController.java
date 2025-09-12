package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.role;


import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.RoleServicePort;
import com.gobikeadventures.gobikeadventuresapplication.dto.role.RoleResponseDTO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.role.RoleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

  private final RoleServicePort roleServicePort;
  private final RoleMapper roleMapper;

  public RoleController(RoleServicePort roleServicePort, RoleMapper roleMapper) {
    this.roleServicePort = roleServicePort;
    this.roleMapper = roleMapper;
  }

  /**
   * GET /roles
   *
   * Retrieves all roles in the system.
   * Access restricted to ADMIN users.
   */
  @GetMapping
  public ResponseEntity<?> getAllRoles() {

    List<RoleResponseDTO> roleDTOs = roleServicePort.getAll()
      .stream()
      .map(roleMapper::toDTO)
      .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(roleDTOs);
  }
}