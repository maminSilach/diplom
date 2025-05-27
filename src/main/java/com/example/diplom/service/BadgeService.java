package com.example.diplom.service;


import com.example.diplom.dto.request.BadgeRequest;
import com.example.diplom.dto.response.BadgeResponse;
import com.example.diplom.entity.Badge;
import com.example.diplom.entity.rules.BadgeRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.mapper.badge.BadgeMapper;
import com.example.diplom.repository.badge.BadgeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeRuleService badgeRuleService;
    private final BadgeMapper badgeMapper;

    @Transactional
    public BadgeResponse createBadge(BadgeRequest badgeRequest) {
        log.info("Start creating badge with name = {}", badgeRequest.getName());

        List<BadgeRules> badgeRules = badgeRequest.getBadgeRuleIds().stream().map(badgeRuleService::loadBadgeRules).toList();
        Badge badgeToSave = badgeMapper.toBadge(badgeRequest, badgeRules);
        Badge savedBadge = badgeRepository.save(badgeToSave);
        badgeRules.forEach(badgeRule -> badgeRule.setBadge(savedBadge));

        log.info("End creating badge with name = {} and id = {}", savedBadge.getName(), savedBadge.getId());

        return badgeMapper.toBadgeResponse(savedBadge);
    }

    public BadgeResponse getBadge(UUID id) {
        log.info("Start getting badge with id = {}", id);
        Badge loadedBadge = loadBadge(id);
        return badgeMapper.toBadgeResponse(loadedBadge);
    }

    public Badge loadBadge(UUID id) {
        return badgeRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Badge with id = " + id + " not found")
                );
    }

    public List<BadgeResponse> getBadges() {
        log.info("Start getting badges");
        List<Badge> loadedBadge = badgeRepository.findAll();

        return loadedBadge.stream()
                .map(badgeMapper::toBadgeResponse)
                .toList();
    }

    @Transactional
    public BadgeResponse updateBadges(UUID id, BadgeRequest badgeRequest) {
        log.info("Start updating badge with id = {} and body = {}", id, badgeRequest);

        List<BadgeRules> badgeRules = badgeRequest.getBadgeRuleIds().stream().map(badgeRuleService::loadBadgeRules).toList();
        Badge loadedBadge = loadBadge(id);
        Badge badgeToUpdate = badgeMapper.updateBadge(loadedBadge, badgeRequest, badgeRules);
        Badge updatedBadge = badgeRepository.save(badgeToUpdate);
        badgeRules.forEach(badgeRule -> badgeRule.setBadge(updatedBadge));

        log.info("End updating badge with name = {} and id = {}", updatedBadge.getName(), updatedBadge.getId());

        return badgeMapper.toBadgeResponse(updatedBadge);
    }

    public void deleteBadge(UUID id) {
        log.info("Start deleting badge with id = {}", id);
        badgeRepository.deleteById(id);
    }
}
