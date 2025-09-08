package com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.user;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserCreateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  // DTO -> Domain Model
  UserDO toModel(UserCreateDTO dto);

  // Domain Model -> DTO
  UserCreateDTO toDTO(UserDO user);
}