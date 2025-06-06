package com.example.diplom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class RatingRuleRequest {

    private String name;

    private String description;

    private Integer count;

    private UUID eventId;
}
