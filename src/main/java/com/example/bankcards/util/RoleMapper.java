package com.example.bankcards.util;

import com.example.bankcards.dto.RoleDTO;
import com.example.bankcards.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleDTO roleDTO);
    RoleDTO toRoleDTO(Role role);
}
