package com.gobikeadventures.gobikeadventuresapplication.domain.port.in;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;

import java.util.List;

public interface RoleServicePort {
  List<RoleDO> getAll();
}