package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RolRepositoryAdapter implements RoleRepositoryPort {
  @Override
  public RoleDO save(RoleDO rolDO) {
    return null;
  }
}