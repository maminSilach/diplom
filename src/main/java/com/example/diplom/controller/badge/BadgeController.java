package com.example.diplom.controller.badge;

import com.example.diplom.dto.request.BadgeRequest;
import com.example.diplom.dto.response.BadgeResponse;
import com.example.diplom.service.BadgeService;
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
@RequestMapping("/badge/v1")
public class BadgeController {

    private final BadgeService badgeService;

    @PostMapping
    public ResponseEntity<BadgeResponse> createBadge(@RequestBody BadgeRequest badgeRequest) {
        BadgeResponse badgeResponse = badgeService.createBadge(badgeRequest);
        return ResponseEntity.ok(badgeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeResponse> getBadge(@PathVariable UUID id) {
        BadgeResponse badgeResponse = badgeService.getBadge(id);
        return ResponseEntity.ok(badgeResponse);
    }

    @GetMapping
    public ResponseEntity<List<BadgeResponse>> getBadges() {
        List<BadgeResponse> badgeResponses = badgeService.getBadges();
        return ResponseEntity.ok(badgeResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BadgeResponse> updateBadge(@PathVariable UUID id, @RequestBody BadgeRequest badgeRequest) {
        BadgeResponse badgeResponse = badgeService.updateBadges(id, badgeRequest);
        return ResponseEntity.ok(badgeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable UUID id) {
        badgeService.deleteBadge(id);
        return ResponseEntity.noContent().build();
    }
}
