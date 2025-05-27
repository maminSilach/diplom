package com.example.diplom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class BadgeRuleResponse {

    private String name;

    private String description;

    private ChallengeResponse challenge;
}
