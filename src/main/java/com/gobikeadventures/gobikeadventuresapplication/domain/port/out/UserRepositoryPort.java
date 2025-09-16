package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;

import java.util.List;

public interface UserRepositoryPort {
  UserDO save(UserDO user);

  UserDO getUserById(Long id);

  List<UserDO> getAll();
}
