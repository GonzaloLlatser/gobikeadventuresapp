package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.User;

public interface UserServicePort {
  User add(User user);
}
