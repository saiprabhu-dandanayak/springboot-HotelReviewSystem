package com.prabhu.ratingservice.service.impl;

import com.prabhu.ratingservice.dto.RatingDTO;
import com.prabhu.ratingservice.entity.Rating;
import com.prabhu.ratingservice.mapper.RatingMapper;
import com.prabhu.ratingservice.repository.RatingRepository;
import com.prabhu.ratingservice.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public Rating createRating(RatingDTO ratingDTO) {
        log.info("Creating Rating: {}", ratingDTO);
        String ratingId = UUID.randomUUID().toString();
        ratingDTO.setRatingId(ratingId);
        Rating rating = ratingMapper.convertToRating(ratingDTO);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(String id) {
        log.info("Fetching Rating with id: {}", id);
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }

    @Override
    public List<Rating> getAllRatings() {
        log.info("Fetching all ratings");
        return ratingRepository.findAll();
    }

    @Override
    public Rating updateRating(String id, RatingDTO ratingDTO) {
        log.info("Updating Rating with id: {}", id);
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        existingRating.setUserId(ratingDTO.getUserId());
        existingRating.setHotelId(ratingDTO.getHotelId());
        existingRating.setRating(ratingDTO.getRating());
        existingRating.setFeedback(ratingDTO.getFeedback());
        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(String id) {
        log.info("Deleting Rating with id: {}", id);
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        log.info("Fetching Ratings by User Id: {}", userId);
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        log.info("Fetching Ratings by Hotel Id: {}", hotelId);
        return ratingRepository.findByHotelId(hotelId);
    }
}
