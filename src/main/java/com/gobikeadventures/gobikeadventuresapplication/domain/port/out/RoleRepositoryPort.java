package com.gobikeadventures.gobikeadventuresapplication.domain.port.out;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;

public interface RoleRepositoryPort {
  RoleDO save(RoleDO rolDO);
}