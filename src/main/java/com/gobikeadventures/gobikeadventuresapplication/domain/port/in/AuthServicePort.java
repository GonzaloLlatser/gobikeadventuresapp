package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;

public interface AuthServicePort {
 AuthDO login (AuthDO authDO);
}