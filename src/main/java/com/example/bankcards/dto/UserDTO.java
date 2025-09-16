package com.example.bankcards.dto;

import com.example.bankcards.entity.Card;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private Set<RoleDTO> roles;
}
