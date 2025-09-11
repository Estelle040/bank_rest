package com.example.bankcards.dto;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private Set<Role> roles;
}
