package com.prabhu.userservice.mapper;



import com.prabhu.userservice.dto.UserDTO;
import com.prabhu.userservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserMapper {

    @Autowired
    ModelMapper modelMapper;
    public UserDTO convertToUserDTO(User user) {
        try {
            log.info(" >>> INSIDE UserMapper: convertToDto() Converting entity to dto");
            log.info(String.valueOf(user));
            return modelMapper.map(user, UserDTO.class);
        } catch (NullPointerException exception) {
            log.error(" >>> INSIDE UserMapper: convertToDto() Converting entity to dto");
            throw new NullPointerException("NullPointerException in converting to dto");
        }
    }

    public User convertToUser(UserDTO userDTO) {
        try {
            log.info(" >>> INSIDE UserMapper: convertToEntity() Converting dto to entity");
            return modelMapper.map(userDTO, User.class);
        } catch (NullPointerException exception) {
            log.error(" >>> INSIDE UserMapper: convertToEntity() Converting dto to entity");
            throw new NullPointerException("NullPointerException in converting to entity");
        }
    }
}
