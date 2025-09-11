package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import java.util.Optional;

public interface AuthRepositoryPort {
  Optional<AuthDO> findByEmail(String email);
}