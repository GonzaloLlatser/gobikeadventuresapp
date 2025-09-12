package com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.auth;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.AuthDO;
import com.gobikeadventures.gobikeadventuresapplication.dto.auth.AuthRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.auth.AuthResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

  @Mapping(target = "token", source = "token")
  @Mapping(target = "role", source ="roleDO" )
  AuthResponseDTO toDTO(AuthDO authDO);

  AuthDO toModel(AuthRequestDTO authRequestDTO);
}