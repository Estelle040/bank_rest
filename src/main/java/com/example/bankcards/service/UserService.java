package com.example.bankcards.service;

import com.example.bankcards.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    void deleteUser(Long id);
    UserDTO getUserByName(String name);
    void getCurrentUser();
    List<UserDTO> getAllUsers();
}
