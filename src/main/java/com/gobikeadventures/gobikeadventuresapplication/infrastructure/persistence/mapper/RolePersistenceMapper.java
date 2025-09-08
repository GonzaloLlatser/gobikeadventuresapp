package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {

  Role toEntity(RoleDO rol);

  RoleDO toDomain(Role role);
}