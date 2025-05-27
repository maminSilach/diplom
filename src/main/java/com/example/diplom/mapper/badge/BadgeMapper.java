package com.example.diplom.mapper.badge;

import com.example.diplom.dto.request.BadgeRequest;
import com.example.diplom.dto.response.BadgeResponse;
import com.example.diplom.entity.Badge;
import com.example.diplom.entity.rules.BadgeRules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface BadgeMapper {

    @Mapping(target = "name", source = "badgeRequest.name")
    @Mapping(target = "description", source = "badgeRequest.description")
    @Mapping(target = "imageUri", source = "badgeRequest.imageUri")
    @Mapping(target = "status", source = "badgeRequest.status")
    @Mapping(target = "badgeRules", source = "badgeRules")
    @Mapping(target = "id", ignore = true)
    Badge toBadge(BadgeRequest badgeRequest, List<BadgeRules> badgeRules);

    BadgeResponse toBadgeResponse(Badge badge);

    @Mapping(target = "name", source = "badgeRequest.name")
    @Mapping(target = "description", source = "badgeRequest.description")
    @Mapping(target = "imageUri", source = "badgeRequest.imageUri")
    @Mapping(target = "status", source = "badgeRequest.status")
    @Mapping(target = "badgeRules", source = "badgeRules")
    @Mapping(target = "id", ignore = true)
    Badge updateBadge(@MappingTarget Badge badge, BadgeRequest badgeRequest, List<BadgeRules> badgeRules);
}
