package com.example.bankcards.util;

import com.example.bankcards.dto.RoleDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role", target = "role", qualifiedByName = "roleDtoToRole")
    User toUser(UserDTO userDTO);

    @Mapping(source = "role", target = "role", qualifiedByName = "roleToRoleDto")
    UserDTO toUserDTO(User user);

    @Named("roleDtoToRole")
    default Role roleDtoToRole(RoleDTO roleDTO) {
        if (roleDTO == null) return null;
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

    @Named("roleToRoleDto")
    default RoleDTO roleToRoleDto(Role role) {
        if (role == null) return null;
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
