package com.example.bankcards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleDTO {
    @Schema(description = "ID роли", example = "1")
    private Long id;

    @Schema(description = "Название роли", example = "ROLE_USER")
    private String name;
}