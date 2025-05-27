package com.example.diplom.mapper.challenge;

import com.example.diplom.dto.request.ChallengeRequest;
import com.example.diplom.dto.response.ChallengeResponse;
import com.example.diplom.entity.Challenge;
import com.example.diplom.entity.rules.ChallengeRules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = ChallengeRuleMapper.class)
public interface ChallengeMapper {

    @Mapping(target = "name", source = "challengeRequest.name")
    @Mapping(target = "description", source = "challengeRequest.description")
    @Mapping(target = "imageUri", source = "challengeRequest.imageUri")
    @Mapping(target = "status", source = "challengeRequest.status")
    @Mapping(target = "challengeRules", source = "challengeRules")
    @Mapping(target = "id", ignore = true)
    Challenge toChallenge(ChallengeRequest challengeRequest, List<ChallengeRules> challengeRules);

    ChallengeResponse toChallengeResponse(Challenge challenge);

    @Mapping(target = "name", source = "challengeRequest.name")
    @Mapping(target = "description", source = "challengeRequest.description")
    @Mapping(target = "imageUri", source = "challengeRequest.imageUri")
    @Mapping(target = "status", source = "challengeRequest.status")
    @Mapping(target = "challengeRules", source = "challengeRules")
    @Mapping(target = "id", ignore = true)
    Challenge updateChallenge(@MappingTarget Challenge challenge, ChallengeRequest challengeRequest, List<ChallengeRules> challengeRules);
}
