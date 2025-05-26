package com.example.diplom.controller.rating;

import com.example.diplom.dto.request.RatingRequest;
import com.example.diplom.dto.response.RatingResponse;
import com.example.diplom.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating/v1")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingResponse> createRating(@RequestBody RatingRequest ratingRequest) {
        RatingResponse ratingResponse = ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(ratingResponse);
    }


}
