package com.example.diplom.service;

import com.example.diplom.dto.request.ChallengeRequest;
import com.example.diplom.dto.response.ChallengeResponse;
import com.example.diplom.entity.Challenge;
import com.example.diplom.entity.rules.ChallengeRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.challenge.ChallengeMapper;
import com.example.diplom.repository.challenge.ChallengeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengeRuleService challengeRuleService;
    private final ChallengeMapper challengeMapper;

    @Transactional
    public ChallengeResponse createChallenge(ChallengeRequest challengeRequest) {
        validateChallengeByName(challengeRequest.getName());
        log.info("Start creating challenge with name = {}", challengeRequest.getName());

        List<ChallengeRules> challengeRules = challengeRequest.getChallengeRuleIds().stream().map(challengeRuleService::loadChallengeRule).toList();
        Challenge challengeToSave = challengeMapper.toChallenge(challengeRequest, challengeRules);
        Challenge savedChallenge = challengeRepository.save(challengeToSave);
        challengeRules.forEach(challengeRule -> challengeRule.setChallenge(savedChallenge));

        log.info("End creating challenge with name = {} and id = {}", savedChallenge.getName(), savedChallenge.getId());

        return challengeMapper.toChallengeResponse(savedChallenge);
    }

    public ChallengeResponse getChallenge(UUID id) {
        log.info("Start getting challenge with id = {}", id);
        Challenge loadedChallenge = loadChallenge(id);
        return challengeMapper.toChallengeResponse(loadedChallenge);
    }

    public Challenge loadChallenge(UUID id) {
        return challengeRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Challenge with id = " + id + " not found")
                );
    }

    public List<ChallengeResponse> getChallenges() {
        log.info("Start getting challenges");
        List<Challenge> loadedChallenge = challengeRepository.findAll();

        return loadedChallenge.stream()
                .map(challengeMapper::toChallengeResponse)
                .toList();
    }

    @Transactional
    public ChallengeResponse updateChallenge(UUID id, ChallengeRequest challengeRequest) {
        log.info("Start updating challenge with id = {} and body = {}", id, challengeRequest);

        List<ChallengeRules> challengeRules = challengeRequest.getChallengeRuleIds().stream().map(challengeRuleService::loadChallengeRule).toList();
        Challenge loadedChallenge = loadChallenge(id);
        Challenge challengeToUpdate = challengeMapper.updateChallenge(loadedChallenge, challengeRequest, challengeRules);
        Challenge updatedChallenge = challengeRepository.save(challengeToUpdate);
        challengeRules.forEach(challengeRule -> challengeRule.setChallenge(updatedChallenge));

        log.info("End updating challenge with name = {} and id = {}", updatedChallenge.getName(), updatedChallenge.getId());

        return challengeMapper.toChallengeResponse(loadedChallenge);
    }

    public void deleteChallenge(UUID id) {
        log.info("Start deleting challenge with id = {}", id);

        validateChallengeById(id);
        challengeRepository.deleteById(id);
    }

    private void validateChallengeByName(String name) {
        if (challengeRepository.existsByName(name)) {
            throw new NotUniqueException("Challenge with name " + name + " already exists");
        }
    }

    private void validateChallengeById(UUID id) {
        if (!challengeRepository.existsById(id)) {
            throw new NotFoundException("Challenge with id " + id + " not found");
        }
    }
}
