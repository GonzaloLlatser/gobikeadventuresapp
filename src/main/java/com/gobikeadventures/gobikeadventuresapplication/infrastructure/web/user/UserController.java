package com.gobikeadventures.gobikeadventuresapplication.infrastructure.web.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.UserServicePort;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserCreateDTO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.user.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/new")
public class UserController {

  private final UserServicePort userServicePort;
  private final UserMapper userMapper;

  public UserController(UserServicePort userServicePort, UserMapper userMapper) {
    this.userServicePort = userServicePort;
    this.userMapper = userMapper;
  }

  @PostMapping
  public ResponseEntity<UserCreateDTO> createUser(@RequestBody UserCreateDTO userDTO) {

    UserDO user = userMapper.toModel(userDTO);
    UserCreateDTO userCreateDTO = userMapper.toDTO(userServicePort.add(user));
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreateDTO);
  }
}
