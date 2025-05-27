package com.example.diplom.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
public class ChallengeRuleRequest {

    private String name;

    private String description;

    private BigDecimal percent;

    private UUID eventId;

    private UUID ratingId;
}
