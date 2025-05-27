package com.example.diplom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BadgeRuleRequest {

    private String name;

    private String description;

    private UUID challengeId;
}
