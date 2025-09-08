package com.gobikeadventures.gobikeadventuresapplication.application.services.user;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.User;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.UserServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepositoryPort;

  public UserService(UserRepositoryPort userRepositoryPort) {
    this.userRepositoryPort = userRepositoryPort;
  }

  @Override
  public User add(User user) {
    return userRepositoryPort.save(user);
  }
}
