package com.prabhu.userservice.service;

import com.prabhu.userservice.dto.UserDTO;
import com.prabhu.userservice.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDTO userDTO);

    User updateUser(String userId, UserDTO userDTO);

    User getUserById(String userId);

    void deleteUser(String userId);

    List<User> getAllUsers();
}
