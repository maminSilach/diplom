package com.example.diplom.controller.rating;

import com.example.diplom.dto.request.RatingRequest;
import com.example.diplom.dto.response.RatingResponse;
import com.example.diplom.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRating(@PathVariable UUID id) {
        RatingResponse ratingRuleResponse = ratingService.getRating(id);
        return ResponseEntity.ok(ratingRuleResponse);
    }

    @GetMapping
    public ResponseEntity<List<RatingResponse>> getRatings() {
        List<RatingResponse> ratingResponses = ratingService.getRatings();
        return ResponseEntity.ok(ratingResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(@PathVariable UUID id, @RequestBody RatingRequest ratingRequest) {
        RatingResponse ratingRuleResponse = ratingService.updateRating(id, ratingRequest);
        return ResponseEntity.ok(ratingRuleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable UUID id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}
