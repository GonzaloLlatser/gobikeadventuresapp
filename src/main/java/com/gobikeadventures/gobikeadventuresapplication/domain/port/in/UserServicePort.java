package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;

public interface UserServicePort {
  UserDO add(UserDO user, Long roleId);
}