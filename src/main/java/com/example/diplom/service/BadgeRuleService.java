package com.example.diplom.service;


import com.example.diplom.dto.request.BadgeRuleRequest;
import com.example.diplom.dto.response.BadgeRuleResponse;
import com.example.diplom.entity.Challenge;
import com.example.diplom.entity.rules.BadgeRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.badge.BadgeRuleMapper;
import com.example.diplom.repository.badge.BadgeRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BadgeRuleService {

    private final BadgeRuleRepository badgeRuleRepository;
    private final BadgeRuleMapper badgeRuleMapper;
    private final ChallengeService challengeService;

    public BadgeRuleResponse createBadgeRule(BadgeRuleRequest badgeRuleRequest) {
        log.info("Start creating badge rule with name = {}", badgeRuleRequest.getName());

        validateBadgeRuleByName(badgeRuleRequest.getName());
        Challenge challenge = challengeService.loadChallenge(badgeRuleRequest.getChallengeId());

        BadgeRules badgeRulesToSave = badgeRuleMapper.toBadgeRules(badgeRuleRequest, challenge);
        BadgeRules savedBadgeRules = badgeRuleRepository.save(badgeRulesToSave);

        log.info("End creating badge rule with name = {} and id = {}", savedBadgeRules.getName(), savedBadgeRules.getId());

        return badgeRuleMapper.toBadgeRuleResponse(savedBadgeRules);
    }

    public BadgeRuleResponse getBadgeRule(UUID id) {
        log.info("Start getting badge rules with id = {}", id);
        BadgeRules loadedBadgeRules = loadBadgeRules(id);
        return badgeRuleMapper.toBadgeRuleResponse(loadedBadgeRules);
    }

    public List<BadgeRuleResponse> getBadgeRules() {
        log.info("Start getting badge rules");
        List<BadgeRules> loadedBadgeRules = badgeRuleRepository.findAll();

        return loadedBadgeRules.stream()
                .map(badgeRuleMapper::toBadgeRuleResponse)
                .toList();
    }

    public BadgeRuleResponse updateBadgeRule(UUID id, BadgeRuleRequest badgeRuleRequest) {
        log.info("Start updating badge rules with id = {} and body = {}", id, badgeRuleRequest);

        validateBadgeRuleByName(badgeRuleRequest.getName());
        Challenge challenge = challengeService.loadChallenge(badgeRuleRequest.getChallengeId());
        BadgeRules badgeRules = loadBadgeRules(id);
        BadgeRules badgeRulesToUpdate = badgeRuleMapper.updateBadgeRules(badgeRules, badgeRuleRequest, challenge);
        BadgeRules updatedbadgeRules = badgeRuleRepository.save(badgeRulesToUpdate);

        log.info("End updating badge rules with name = {} and id = {}", updatedbadgeRules.getName(), updatedbadgeRules.getId());

        return badgeRuleMapper.toBadgeRuleResponse(updatedbadgeRules);
    }

    public void deleteBadgeRule(UUID id) {
        log.info("Start deleting badge rule with id = {}", id);
        badgeRuleRepository.deleteById(id);
    }

    public BadgeRules loadBadgeRules(UUID id) {
        return badgeRuleRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Badge rule with id = " + id + " not found")
                );
    }

    private void validateBadgeRuleByName(String name) {
        if (badgeRuleRepository.existsByName(name)) {
            throw new NotUniqueException("Badge rule with name " + name + " already exists");
        }
    }
}
