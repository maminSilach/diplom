package com.example.diplom.mapper.challenge;

import com.example.diplom.dto.request.ChallengeRuleRequest;
import com.example.diplom.dto.response.ChallengeRuleResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.Rating;
import com.example.diplom.entity.rules.ChallengeRules;
import com.example.diplom.mapper.event.EventMapper;
import com.example.diplom.mapper.rating.RatingMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {RatingMapper.class, EventMapper.class} )
public interface ChallengeRuleMapper {

    @Mapping(target = "event", source = "event")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "name", source = "challengeRuleRequest.name")
    @Mapping(target = "description", source = "challengeRuleRequest.description")
    @Mapping(target = "percent", source = "challengeRuleRequest.percent")
    @Mapping(target = "id", ignore = true)
    ChallengeRules toChallengeRule(ChallengeRuleRequest challengeRuleRequest, Event event, Rating rating);

    ChallengeRuleResponse toChallengeRuleResponse(ChallengeRules challengeRule);

    @Mapping(target = "event", source = "event")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "name", source = "challengeRuleRequest.name")
    @Mapping(target = "description", source = "challengeRuleRequest.description")
    @Mapping(target = "percent", source = "challengeRuleRequest.percent")
    @Mapping(target = "id", ignore = true)
    ChallengeRules updateChallengeRule(@MappingTarget ChallengeRules challengeRule, ChallengeRuleRequest challengeRuleRequest, Event event, Rating rating);
}
