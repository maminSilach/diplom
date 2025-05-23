package com.example.diplom.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
public class EventResponse {

    private UUID id;

    private String name;

    private String description;

    private LocalDateTime created;
}
