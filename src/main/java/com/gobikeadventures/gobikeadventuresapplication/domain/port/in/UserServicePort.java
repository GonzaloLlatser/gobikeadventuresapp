package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;

import java.util.List;
import java.util.UUID;

public interface UserServicePort {
  UserDO add(UserDO user, Long roleId);

  UserDO getUserById(UUID id);

  List<UserDO> getAll();
}