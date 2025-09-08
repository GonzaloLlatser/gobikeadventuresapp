package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;

public interface UserRepositoryPort  {
  UserDO save(UserDO user);
}
