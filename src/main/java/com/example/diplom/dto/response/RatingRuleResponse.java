package com.example.diplom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingRuleResponse {

    private UUID id;

    private String name;

    private String description;

    private Integer count;

    private EventResponse event;
}
