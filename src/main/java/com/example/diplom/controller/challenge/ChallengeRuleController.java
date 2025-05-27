package com.example.diplom.controller.challenge;


import com.example.diplom.dto.request.ChallengeRuleRequest;
import com.example.diplom.dto.response.ChallengeRuleResponse;
import com.example.diplom.service.ChallengeRuleService;
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
@RequestMapping("/challenge-rules/v1")
@RequiredArgsConstructor
public class ChallengeRuleController {

    private final ChallengeRuleService challengeRuleService;

    @PostMapping
    public ResponseEntity<ChallengeRuleResponse> createChallengeRule(@RequestBody ChallengeRuleRequest challengeRuleRequest) {
        ChallengeRuleResponse challengeRuleResponse = challengeRuleService.createChallengeRule(challengeRuleRequest);
        return ResponseEntity.ok(challengeRuleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeRuleResponse> getChallengeRule(@PathVariable UUID id) {
        ChallengeRuleResponse challengeRuleResponse = challengeRuleService.getChallengeRule(id);
        return ResponseEntity.ok(challengeRuleResponse);
    }

    @GetMapping
    public ResponseEntity<List<ChallengeRuleResponse>> getChallengeRules() {
        List<ChallengeRuleResponse> challengeRuleResponses = challengeRuleService.getChallengeRules();
        return ResponseEntity.ok(challengeRuleResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChallengeRuleResponse> updateChallengeRules(@PathVariable UUID id, @RequestBody ChallengeRuleRequest challengeRuleRequest) {
        ChallengeRuleResponse challengeRuleResponse = challengeRuleService.updateChallengeRule(id, challengeRuleRequest);
        return ResponseEntity.ok(challengeRuleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallengeRules(@PathVariable UUID id) {
        challengeRuleService.deleteChallengeRule(id);
        return ResponseEntity.noContent().build();
    }
}
