package com.prabhu.hotelservice.mapper;

import com.prabhu.hotelservice.dto.HotelDTO;
import com.prabhu.hotelservice.entity.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HotelMapper {

    @Autowired
    private ModelMapper modelMapper;

    public HotelDTO convertToHotelDTO(Hotel hotel) {
        try {
            log.info(">>> INSIDE HotelMapper: convertToHotelDTO() Converting entity to DTO");
            return modelMapper.map(hotel, HotelDTO.class);
        } catch (NullPointerException exception) {
            log.error(">>> INSIDE HotelMapper: convertToHotelDTO() Converting entity to DTO");
            throw new NullPointerException("NullPointerException in converting to DTO");
        }
    }

    public Hotel convertToHotel(HotelDTO hotelDTO) {
        try {
            log.info(">>> INSIDE HotelMapper: convertToHotel() Converting DTO to entity");
            return modelMapper.map(hotelDTO, Hotel.class);
        } catch (NullPointerException exception) {
            log.error(">>> INSIDE HotelMapper: convertToHotel() Converting DTO to entity");
            throw new NullPointerException("NullPointerException in converting to entity");
        }
    }
}
