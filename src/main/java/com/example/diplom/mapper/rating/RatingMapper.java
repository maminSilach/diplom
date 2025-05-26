package com.example.diplom.mapper.rating;

import com.example.diplom.dto.request.RatingRequest;
import com.example.diplom.dto.response.RatingResponse;
import com.example.diplom.entity.Rating;
import com.example.diplom.entity.rules.RatingRules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {RatingRuleMapper.class})
public interface RatingMapper {

    @Mapping(target = "name", source = "ratingRequest.name")
    @Mapping(target = "description", source = "ratingRequest.description")
    @Mapping(target = "imageUri", source = "ratingRequest.imageUri")
    @Mapping(target = "status", source = "ratingRequest.status")
    @Mapping(target = "ratingRules", source = "ratingRules")
    @Mapping(target = "id", ignore = true)
    Rating toRating(RatingRequest ratingRequest, List<RatingRules> ratingRules);

    RatingResponse toRatingResponse(Rating rating);

    @Mapping(target = "name", source = "ratingRequest.name")
    @Mapping(target = "description", source = "ratingRequest.description")
    @Mapping(target = "imageUri", source = "ratingRequest.imageUri")
    @Mapping(target = "status", source = "ratingRequest.status")
    @Mapping(target = "ratingRules", source = "ratingRules")
    @Mapping(target = "id", ignore = true)
    Rating updateRating(@MappingTarget Rating rating, RatingRequest ratingRequest, List<RatingRules> ratingRules);
}
