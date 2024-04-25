package com.prabhu.hotelservice.service;

import com.prabhu.hotelservice.dto.HotelDTO;
import com.prabhu.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(HotelDTO hotelDTO);

    Hotel getHotelById(String id);

    List<Hotel> getAllHotels();

    Hotel updateHotel(String id, HotelDTO hotelDTO);

    void deleteHotel(String id);
}
