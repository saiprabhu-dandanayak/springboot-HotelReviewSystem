package com.prabhu.ratingservice.service;

import com.prabhu.ratingservice.dto.RatingDTO;
import com.prabhu.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(RatingDTO ratingDTO);

    Rating getRatingById(String id);

    List<Rating> getAllRatings();

    Rating updateRating(String id, RatingDTO ratingDTO);

    void deleteRating(String id);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
