package com.gobikeadventures.gobikeadventuresapplication.application.services.role;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.RoleServicePort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServicePort {

  private final RoleRepositoryPort roleRepositoryPort;

  public RoleService(RoleRepositoryPort roleRepositoryPort) {
    this.roleRepositoryPort = roleRepositoryPort;
  }

  public List<RoleDO> getAll() {
    return roleRepositoryPort.getAll();
  }
}
