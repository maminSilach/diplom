package com.example.diplom.dto.request;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RatingRuleRequest {

    private String name;

    private String description;

    private Integer count;

    private UUID ratingId;

    private UUID eventId;
}
