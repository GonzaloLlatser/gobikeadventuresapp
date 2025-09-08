package com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.mapper;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.User;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  UserEntity toEntity(User user);

  User toDomain(UserEntity userEntity);
}
