package com.example.diplom.service;

import com.example.diplom.dto.request.ChallengeRuleRequest;
import com.example.diplom.dto.response.ChallengeRuleResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.Rating;
import com.example.diplom.entity.rules.ChallengeRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.challenge.ChallengeRuleMapper;
import com.example.diplom.repository.challenge.ChallengeRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeRuleService {

    private final ChallengeRuleRepository challengeRuleRepository;
    private final ChallengeRuleMapper challengeRuleMapper;
    private final EventService eventService;
    private final RatingService ratingService;

    public ChallengeRuleResponse createChallengeRule(ChallengeRuleRequest challengeRuleRequest) {
        log.info("Start creating challenge rule with name = {}", challengeRuleRequest.getName());

        validateChallengeRuleByName(challengeRuleRequest.getName());
        Event event = eventService.loadEvent(challengeRuleRequest.getEventId());
        Rating rating = ratingService.loadRating(challengeRuleRequest.getRatingId());

        ChallengeRules challengeRulesToSave = challengeRuleMapper.toChallengeRule(challengeRuleRequest, event, rating);
        ChallengeRules savedChallengeRules = challengeRuleRepository.save(challengeRulesToSave);

        log.info("End creating challenge rule with name = {} and id = {}", savedChallengeRules.getName(), savedChallengeRules.getId());

        return challengeRuleMapper.toChallengeRuleResponse(savedChallengeRules);
    }

    public ChallengeRuleResponse getChallengeRule(UUID id) {
        log.info("Start getting challenge rule with id = {}", id);
        ChallengeRules loadedChallengeRules = loadChallengeRule(id);
        return challengeRuleMapper.toChallengeRuleResponse(loadedChallengeRules);
    }

    public ChallengeRuleResponse updateChallengeRule(UUID id, ChallengeRuleRequest challengeRuleRequest) {
        log.info("Start updating challenge rules with id = {} and body = {}", id, challengeRuleRequest);

        Event event = eventService.loadEvent(challengeRuleRequest.getEventId());
        Rating rating = ratingService.loadRating(challengeRuleRequest.getRatingId());
        ChallengeRules challengeRules = loadChallengeRule(id);
        ChallengeRules challengeRulesToUpdate = challengeRuleMapper.updateChallengeRule(challengeRules, challengeRuleRequest, event, rating);
        ChallengeRules updatedChallengeRule = challengeRuleRepository.save(challengeRulesToUpdate);

        log.info("End updating challenge rules with name = {} and id = {}", updatedChallengeRule.getName(), updatedChallengeRule.getId());

        return challengeRuleMapper.toChallengeRuleResponse(updatedChallengeRule);
    }

    public void deleteChallengeRule(UUID id) {
        log.info("Start deleting challenge rule with id = {}", id);

        validateChallengeRuleById(id);
        challengeRuleRepository.deleteById(id);
    }

    public ChallengeRules loadChallengeRule(UUID id) {
        return challengeRuleRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Challenge rule with id = " + id + " not found")
                );
    }

    public List<ChallengeRuleResponse> getChallengeRules() {
        log.info("Start getting challenge rules");
        List<ChallengeRules> loadedChallengeRules = challengeRuleRepository.findAll();

        return loadedChallengeRules.stream()
                .map(challengeRuleMapper::toChallengeRuleResponse)
                .toList();
    }

    private void validateChallengeRuleByName(String name) {
        if (challengeRuleRepository.existsByName(name)) {
            throw new NotUniqueException("Challenge rule with name " + name + " already exists");
        }
    }

    private void validateChallengeRuleById(UUID id) {
        if (!challengeRuleRepository.existsById(id)) {
            throw new NotFoundException("Challenge rule with id " + id + " not found");
        }
    }
}
