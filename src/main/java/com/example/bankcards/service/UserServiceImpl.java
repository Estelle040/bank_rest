package com.example.bankcards.service;

import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.RoleRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.PasswordUtil;
import com.example.bankcards.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordUtil passwordUtil;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        Role role = roleRepository.findById(userDTO.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);
        user.setPassword(passwordUtil.encryptPassword(user.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.toUserDTO(saved);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return userMapper.toUserDTO(userRepository.save(userMapper.toUser(userDTO)));
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userMapper.toUserDTO(user);
    }


    @Override
    public UserDTO getUserByName(String name) {
        return userMapper.toUserDTO(userRepository.findByName(name));
    }


    @Override
    public void getCurrentUser() {
        return;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
