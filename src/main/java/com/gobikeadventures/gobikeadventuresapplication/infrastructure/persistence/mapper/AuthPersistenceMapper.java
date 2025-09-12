package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthPersistenceMapper {

  @Mapping(target = "roleDO", source = "rol")
  AuthDO toDomain(User user);
}
