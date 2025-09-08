package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.User;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  User toEntity(com.gobikeadventures.gobikeadventuresapplication.domain.model.User user);

  com.gobikeadventures.gobikeadventuresapplication.domain.model.User toDomain(User userEntity);
}
