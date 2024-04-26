package com.prabhu.userservice.external.services;

import com.prabhu.userservice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "hotel-service")
public interface HotelService {

    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
