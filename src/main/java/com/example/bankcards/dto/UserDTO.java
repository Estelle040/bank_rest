package com.example.bankcards.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserDTO {
    @Schema(description = "ID пользователя", example = "10")
    private Long id;

    @Schema(description = "Имя пользователя", example = "IVAN IVANOV")
    private String name;

    @Schema(description = "Пароль", example = "123456")
    private String password;

    @Schema(description = "Роли пользователя")
    private RoleDTO role;
}