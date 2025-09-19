package com.example.bankcards.util;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.RoleDTO;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CardMapper {
    @Mapping(source = "owner", target = "owner", qualifiedByName = "userToUserDTO")
    CardDTO toCardDTO(Card card);
    @Mapping(source = "owner", target = "owner", qualifiedByName = "userDtoToUser")
    Card toCard(CardDTO cardDTO);

    @Named("userToUserDTO")
    default UserDTO toUserDTO(User user){
        if(user == null) return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        if(user.getRole() != null) {
            Role role = user.getRole();
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            userDTO.setRole(roleDTO);
        }
        return userDTO;
    }

    @Named("userDtoToUser")
    default User toUser(UserDTO userDTO){
        if(userDTO == null) return null;
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        if(userDTO.getRole() != null) {
            RoleDTO roleDTO = userDTO.getRole();
            Role role = new Role();
            role.setId(roleDTO.getId());
            role.setName(roleDTO.getName());
            user.setRole(role);
        }
        return user;
    }
}
