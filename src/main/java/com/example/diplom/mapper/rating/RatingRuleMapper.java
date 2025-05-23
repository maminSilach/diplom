package com.example.diplom.mapper.rating;


import com.example.diplom.dto.request.RatingRuleRequest;
import com.example.diplom.dto.response.RatingRuleResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.rules.RatingRules;
import com.example.diplom.mapper.event.EventMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = EventMapper.class)
public interface RatingRuleMapper {

    @Mapping(target = "event", source = "event")
    @Mapping(target = "name", source = "ratingRuleRequest.name")
    @Mapping(target = "description", source = "ratingRuleRequest.description")
    @Mapping(target = "id",ignore = true)
    RatingRules toRatingRules(RatingRuleRequest ratingRuleRequest, Event event);

    RatingRuleResponse toRatingRulesResponse(RatingRules ratingRules);

    @Mapping(target = "event", source = "event")
    @Mapping(target = "name", source = "ratingRuleRequest.name")
    @Mapping(target = "description", source = "ratingRuleRequest.description")
    @Mapping(target = "id",ignore = true)
    RatingRules updateRatingRules(@MappingTarget RatingRules ratingRules, RatingRuleRequest ratingRuleRequest, Event event);
}
