package com.example.diplom.dto.request;


import com.example.diplom.entity.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RatingRequest {

    public String name;

    public String description;

    public String imageUri;

    public Integer rules;

    public EntityStatus status;

    public List<UUID> ratingRuleIds = new ArrayList<>();
}
