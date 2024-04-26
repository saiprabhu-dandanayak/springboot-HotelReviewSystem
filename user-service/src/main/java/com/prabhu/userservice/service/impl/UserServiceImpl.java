package com.prabhu.userservice.service.impl;

import com.prabhu.userservice.dto.UserDTO;
import com.prabhu.userservice.entity.Hotel;
import com.prabhu.userservice.entity.Rating;
import com.prabhu.userservice.entity.User;
import com.prabhu.userservice.exception.UserNotFoundException;
import com.prabhu.userservice.external.services.HotelService;
import com.prabhu.userservice.mapper.UserMapper;
import com.prabhu.userservice.repository.UserRepository;
import com.prabhu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final  HotelService hotelService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,RestTemplate restTemplate,HotelService hotelService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.restTemplate=restTemplate;
        this.hotelService=hotelService;
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
        Rating[] ratingOfUsers = restTemplate.getForObject("http://RATING-SERVICE/api/v1/ratings/user/"+user.getUserId(), Rating[] .class);
        List<Rating> ratings = Arrays.stream(ratingOfUsers).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://hotel-service/api/v1/hotels/"+rating.getHotelId(),Hotel.class);
//          Hotel hotel=  forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
          rating.setHotel(hotel);
          return  rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Rating[] ratingOfUsers = restTemplate.getForObject("http://rating-service/api/v1/ratings/user/" + user.getUserId(), Rating[].class);
            List<Rating> ratings = Arrays.stream(ratingOfUsers).toList();
            List<Rating> ratingList = ratings.stream().map(rating -> {
                ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://hotel-service/api/v1/hotels/" + rating.getHotelId(), Hotel.class);
                Hotel hotel = forEntity.getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
        }
        return users;

    }
}
