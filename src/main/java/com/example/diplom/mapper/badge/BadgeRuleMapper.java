package com.example.diplom.mapper.badge;

import com.example.diplom.dto.request.BadgeRuleRequest;
import com.example.diplom.dto.response.BadgeRuleResponse;
import com.example.diplom.entity.Challenge;
import com.example.diplom.entity.rules.BadgeRules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface BadgeRuleMapper {

    @Mapping(target = "challenge", source = "challenge")
    @Mapping(target = "name", source = "badgeRuleRequest.name")
    @Mapping(target = "description", source = "badgeRuleRequest.description")
    @Mapping(target = "id", ignore = true)
    BadgeRules toBadgeRules(BadgeRuleRequest badgeRuleRequest, Challenge challenge);

    BadgeRuleResponse toBadgeRuleResponse(BadgeRules badgeRules);

    @Mapping(target = "challenge", source = "challenge")
    @Mapping(target = "name", source = "badgeRuleRequest.name")
    @Mapping(target = "description", source = "badgeRuleRequest.description")
    @Mapping(target = "id", ignore = true)
    BadgeRules updateBadgeRules(@MappingTarget BadgeRules badgeRules, BadgeRuleRequest badgeRuleRequest, Challenge challenge);
}
