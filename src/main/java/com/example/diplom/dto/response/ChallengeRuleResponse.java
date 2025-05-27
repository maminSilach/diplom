package com.example.diplom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChallengeRuleResponse {

    private UUID id ;

    private String name;

    private String description;

    private BigDecimal percent;

    private RatingResponse rating;

    private EventResponse event;
}
