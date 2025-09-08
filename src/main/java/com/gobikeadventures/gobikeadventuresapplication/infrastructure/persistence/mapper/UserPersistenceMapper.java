package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  User toEntity(UserDO user);

  UserDO toDomain(User userEntity);
}