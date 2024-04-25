package com.prabhu.ratingservice.mapper;

import com.prabhu.ratingservice.dto.RatingDTO;
import com.prabhu.ratingservice.entity.Rating;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RatingMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RatingDTO convertToRatingDTO(Rating rating) {
        try {
            log.info(">>> INSIDE RatingMapper: convertToRatingDTO() Converting entity to DTO");
            return modelMapper.map(rating, RatingDTO.class);
        } catch (NullPointerException exception) {
            log.error(">>> INSIDE RatingMapper: convertToRatingDTO() Converting entity to DTO");
            throw new NullPointerException("NullPointerException in converting to DTO");
        }
    }

    public Rating convertToRating(RatingDTO ratingDTO) {
        try {
            log.info(">>> INSIDE RatingMapper: convertToRating() Converting DTO to entity");
            return modelMapper.map(ratingDTO, Rating.class);
        } catch (NullPointerException exception) {
            log.error(">>> INSIDE RatingMapper: convertToRating() Converting DTO to entity");
            throw new NullPointerException("NullPointerException in converting to entity");
        }
    }
}
