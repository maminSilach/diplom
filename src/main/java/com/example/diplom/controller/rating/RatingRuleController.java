package com.example.diplom.controller.rating;

import com.example.diplom.dto.request.RatingRuleRequest;
import com.example.diplom.dto.response.RatingRuleResponse;
import com.example.diplom.service.RatingRuleService;
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
@RequestMapping("/rating-rules/v1")
@RequiredArgsConstructor
public class RatingRuleController {

    private final RatingRuleService ratingRuleService;

    @PostMapping
    public ResponseEntity<RatingRuleResponse> createRatingRules(@RequestBody RatingRuleRequest ratingRuleRequest) {
        RatingRuleResponse eventResponse = ratingRuleService.createRatingRules(ratingRuleRequest);
        return ResponseEntity.ok(eventResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingRuleResponse> getRatingRule(@PathVariable UUID id) {
        RatingRuleResponse ratingRuleResponse = ratingRuleService.getRatingRules(id);
        return ResponseEntity.ok(ratingRuleResponse);
    }

    @GetMapping
    public ResponseEntity<List<RatingRuleResponse>> getRatingRule() {
        List<RatingRuleResponse> ratingRulesResponses = ratingRuleService.getRatingRules();
        return ResponseEntity.ok(ratingRulesResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RatingRuleResponse> updateRatingRules(@PathVariable UUID id, @RequestBody RatingRuleRequest ratingRuleRequest) {
        RatingRuleResponse ratingRuleResponse = ratingRuleService.updateEvent(id, ratingRuleRequest);
        return ResponseEntity.ok(ratingRuleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRatingRule(@PathVariable UUID id) {
        ratingRuleService.deleteRatingRule(id);
        return ResponseEntity.noContent().build();
    }
}
