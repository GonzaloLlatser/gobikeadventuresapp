package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
  Optional<RoleDO> findById(String id);
}