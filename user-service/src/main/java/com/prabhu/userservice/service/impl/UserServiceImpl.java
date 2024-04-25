package com.prabhu.userservice.service.impl;

import com.prabhu.userservice.dto.UserDTO;
import com.prabhu.userservice.entity.User;
import com.prabhu.userservice.exception.UserNotFoundException;
import com.prabhu.userservice.mapper.UserMapper;
import com.prabhu.userservice.repository.UserRepository;
import com.prabhu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        String randomUserId = UUID.randomUUID().toString();
        userDTO.setUserId(randomUserId);
        User user = userMapper.convertToUser(userDTO);
        return userRepository.save(user);
    }


    @Override
    public User updateUser(String userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        User updatedUser = userMapper.convertToUser(userDTO);
        updatedUser.setUserId(userId);
        return userRepository.save(updatedUser);
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
