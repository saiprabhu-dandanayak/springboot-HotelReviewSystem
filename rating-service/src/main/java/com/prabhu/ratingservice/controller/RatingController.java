package com.prabhu.ratingservice.controller;

import com.prabhu.ratingservice.dto.RatingDTO;
import com.prabhu.ratingservice.entity.Rating;
import com.prabhu.ratingservice.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody RatingDTO ratingDTO) {
        log.info("Creating Rating: {}", ratingDTO);
        Rating rating = ratingService.createRating(ratingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable String id) {
        log.info("Fetching Rating with id: {}", id);
        Rating rating = ratingService.getRatingById(id);
        return ResponseEntity.ok().body(rating);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        log.info("Fetching all ratings");
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok().body(ratings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable String id, @RequestBody RatingDTO ratingDTO) {
        log.info("Updating Rating with id: {}", id);
        Rating updatedRating = ratingService.updateRating(id, ratingDTO);
        return ResponseEntity.ok().body(updatedRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable String id) {
        log.info("Deleting Rating with id: {}", id);
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        log.info("Fetching Ratings by User Id: {}", userId);
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok().body(ratings);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        log.info("Fetching Ratings by Hotel Id: {}", hotelId);
        List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok().body(ratings);
    }
}
