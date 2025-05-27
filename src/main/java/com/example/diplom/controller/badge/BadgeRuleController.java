package com.example.diplom.controller.badge;


import com.example.diplom.dto.request.BadgeRuleRequest;
import com.example.diplom.dto.response.BadgeRuleResponse;
import com.example.diplom.service.BadgeRuleService;
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
@RequestMapping("/badge-rule/v1")
@RequiredArgsConstructor
public class BadgeRuleController {

    private final BadgeRuleService badgeRuleService;

    @PostMapping
    public ResponseEntity<BadgeRuleResponse> createBadgeRules(@RequestBody BadgeRuleRequest badgeRuleRequest) {
        BadgeRuleResponse badgeRuleResponse = badgeRuleService.createBadgeRule(badgeRuleRequest);
        return ResponseEntity.ok(badgeRuleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeRuleResponse> getBadgeRule(@PathVariable UUID id) {
        BadgeRuleResponse badgeRuleResponse = badgeRuleService.getBadgeRule(id);
        return ResponseEntity.ok(badgeRuleResponse);
    }

    @GetMapping
    public ResponseEntity<List<BadgeRuleResponse>> getBadgeRules() {
        List<BadgeRuleResponse> badgeRuleResponses = badgeRuleService.getBadgeRules();
        return ResponseEntity.ok(badgeRuleResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BadgeRuleResponse> updateBadgeRules(@PathVariable UUID id, @RequestBody BadgeRuleRequest badgeRuleRequest) {
        BadgeRuleResponse badgeRuleResponses = badgeRuleService.updateBadgeRule(id, badgeRuleRequest);
        return ResponseEntity.ok(badgeRuleResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadgeRule(@PathVariable UUID id) {
        badgeRuleService.deleteBadgeRule(id);
        return ResponseEntity.noContent().build();
    }
}
