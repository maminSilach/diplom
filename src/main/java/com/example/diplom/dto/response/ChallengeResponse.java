package com.example.diplom.dto.response;


import com.example.diplom.entity.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ChallengeResponse {

    private UUID id;

    private String name;

    private String description;

    private String imageUri;

    private EntityStatus status;

    private LocalDateTime created;

    private List<ChallengeRuleResponse> challengeRules;
}
