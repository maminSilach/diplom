package com.example.diplom.controller.challenge;


import com.example.diplom.dto.request.ChallengeRequest;
import com.example.diplom.dto.response.ChallengeResponse;
import com.example.diplom.service.ChallengeService;
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
@RequestMapping("/challenge/v1")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ChallengeResponse> createChallenge(@RequestBody ChallengeRequest challengeRequest) {
        ChallengeResponse challengeResponse = challengeService.createChallenge(challengeRequest);
        return ResponseEntity.ok(challengeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeResponse> getChallenge(@PathVariable UUID id) {
        ChallengeResponse challengeResponse = challengeService.getChallenge(id);
        return ResponseEntity.ok(challengeResponse);
    }

    @GetMapping
    public ResponseEntity<List<ChallengeResponse>> getChallenges() {
        List<ChallengeResponse> challengeResponses = challengeService.getChallenges();
        return ResponseEntity.ok(challengeResponses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChallengeResponse> updateChallenge(@PathVariable UUID id, @RequestBody ChallengeRequest challengeRequest) {
        ChallengeResponse challengeResponse = challengeService.updateChallenge(id, challengeRequest);
        return ResponseEntity.ok(challengeResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable UUID id) {
        challengeService.deleteChallenge(id);
        return ResponseEntity.noContent().build();
    }
}
