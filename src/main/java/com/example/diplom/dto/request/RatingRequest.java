package com.example.diplom.dto.request;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
public class RatingRequest {

    private final String name;

    private final String description;

    private final MultipartFile image;

    private final Integer rules;

    private List<UUID> ratingRuleIds = new ArrayList<>();
}
