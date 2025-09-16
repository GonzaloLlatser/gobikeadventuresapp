package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  @Mapping(target = "rol.id", source = "rol.id")
  User toEntity(UserDO user);

  @Mapping(target = "rol.id", source = "rol.id")
  UserDO toDomain(User userEntity);
}