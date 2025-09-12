package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryPort {
  UserDO save(UserDO user);

  UserDO getUserById(UUID uuid);

  List<UserDO> getAll();
}
