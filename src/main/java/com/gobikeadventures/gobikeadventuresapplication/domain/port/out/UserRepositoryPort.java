package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.User;

public interface UserRepositoryPort  {
  User save(User user);
}
