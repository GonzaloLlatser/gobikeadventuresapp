package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.adapter;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper.RolePersistenceMapper;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.SpringRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolRepositoryAdapter implements RoleRepositoryPort {

  private final SpringRoleRepository springRoleRepository;
  private final RolePersistenceMapper rolePersistenceMapper;

  public RolRepositoryAdapter(SpringRoleRepository springRoleRepository, RolePersistenceMapper rolePersistenceMapper) {
    this.springRoleRepository = springRoleRepository;
    this.rolePersistenceMapper = rolePersistenceMapper;
  }

  @Override
  public Optional<RoleDO> findById(String id) {
    return springRoleRepository.findById(Long.valueOf(id))
      .map(rolePersistenceMapper::toDomain);
  }
}