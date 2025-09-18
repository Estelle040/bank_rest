package com.example.bankcards.util;

import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-18T15:52:24+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.role( roleDtoToRole( userDTO.getRole() ) );
        user.id( userDTO.getId() );
        user.name( userDTO.getName() );
        user.password( userDTO.getPassword() );

        return user.build();
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRole( roleToRoleDto( user.getRole() ) );
        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }
}
