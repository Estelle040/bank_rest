package com.example.bankcards.dto;

import com.example.bankcards.entity.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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