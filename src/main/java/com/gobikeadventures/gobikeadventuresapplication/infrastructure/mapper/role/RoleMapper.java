package com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.role;

import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;

import com.gobikeadventures.gobikeadventuresapplication.dto.role.RoleResponseDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleResponseDTO toDTO(RoleDO role);
}