package com.gobikeadventures.gobikeadventuresapplication.infrastructure.mapper.user;


import com.gobikeadventures.gobikeadventuresapplication.domain.model.RoleDO;
import com.gobikeadventures.gobikeadventuresapplication.domain.model.UserDO;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.RoleResponseDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserRequestDTO;
import com.gobikeadventures.gobikeadventuresapplication.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "rol", ignore = true)
  @Mapping(target = "id", ignore = true)
  UserDO toModel(UserRequestDTO userRequestDTO);

  @Mapping(target = "rol.permission", source = "rol.permissionDescription")
  UserResponseDTO toDTO(UserDO user);

  RoleResponseDTO toRoleResponse(RoleDO role);
}