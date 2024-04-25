package com.prabhu.hotelservice.service.impl;

import com.prabhu.hotelservice.dto.HotelDTO;
import com.prabhu.hotelservice.entity.Hotel;
import com.prabhu.hotelservice.mapper.HotelMapper;
import com.prabhu.hotelservice.repository.HotelRepository;
import com.prabhu.hotelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public Hotel createHotel(HotelDTO hotelDTO) {
        log.info("Creating Hotel: {}", hotelDTO);
        String hotelId = UUID.randomUUID().toString();
        hotelDTO.setId(hotelId);
        Hotel hotel = hotelMapper.convertToHotel(hotelDTO);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(String id) {
        log.info("Fetching Hotel with id: {}", id);
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        log.info("Fetching all hotels");
        return hotelRepository.findAll();
    }

    @Override
    public Hotel updateHotel(String id, HotelDTO hotelDTO) {
        log.info("Updating Hotel with id: {}", id);
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));

        Hotel updatedHotel = hotelMapper.convertToHotel(hotelDTO);
        updatedHotel.setId(id);
        return hotelRepository.save(updatedHotel);
    }

    @Override
    public void deleteHotel(String id) {
        log.info("Deleting Hotel with id: {}", id);
        hotelRepository.deleteById(id);
    }
}

