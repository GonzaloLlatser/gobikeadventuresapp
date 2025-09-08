package com.gobikeadventures.gobikeadventuresapplication.application.services.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.UserServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepositoryPort;
  private final PasswordEncoderPort passwordEncoderPort;

  public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
    this.userRepositoryPort = userRepositoryPort;
    this.passwordEncoderPort = passwordEncoderPort;
  }

  @Override
  public UserDO add(UserDO user) {
    // Password hash
    String hashedPassword = passwordEncoderPort.encode(user.getPassword());
    user.setPassword(hashedPassword);
    return userRepositoryPort.save(user);
  }
}
