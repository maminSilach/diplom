package com.example.diplom.service;

import com.example.diplom.dto.request.RatingRuleRequest;
import com.example.diplom.dto.response.RatingRuleResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.rules.RatingRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.rating.RatingRuleMapper;
import com.example.diplom.repository.rating.RatingRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class RatingRuleService {

    private final RatingRuleRepository ratingRuleRepository;
    private final EventService eventService;
    private final RatingRuleMapper ratingRuleMapper;

    public RatingRuleResponse createRatingRules(RatingRuleRequest ratingRuleRequest) {
        log.info("Start creating rating rule with name = {}", ratingRuleRequest.getName());

        validateRatingRuleByName(ratingRuleRequest.getName());
        Event event = eventService.loadEvent(ratingRuleRequest.getEventId());

        RatingRules ratingRulesToSave = ratingRuleMapper.toRatingRules(ratingRuleRequest, event);
        RatingRules savedRatingRules = ratingRuleRepository.save(ratingRulesToSave);

        log.info("End creating rating rule with name = {} and id = {}", savedRatingRules.getName(), savedRatingRules.getId());

        return ratingRuleMapper.toRatingRulesResponse(savedRatingRules);
    }

    public RatingRuleResponse getRatingRules(UUID id) {
        log.info("Start getting rating rules with id = {}", id);
        RatingRules loadedRatingRules = loadRatingRules(id);
        return ratingRuleMapper.toRatingRulesResponse(loadedRatingRules);
    }

    public RatingRuleResponse updateRatingRule(UUID id, RatingRuleRequest ratingRuleRequest) {
        log.info("Start updating rating rules with id = {} and body = {}", id, ratingRuleRequest);

        validateRatingRuleByName(ratingRuleRequest.getName());
        Event event = eventService.loadEvent(ratingRuleRequest.getEventId());
        RatingRules ratingRules = loadRatingRules(id);
        RatingRules ratingRulesToUpdate = ratingRuleMapper.updateRatingRules(ratingRules, ratingRuleRequest, event);
        RatingRules updatedRatingRules = ratingRuleRepository.save(ratingRulesToUpdate);

        log.info("End updating rating rules with name = {} and id = {}", updatedRatingRules.getName(), updatedRatingRules.getId());

        return ratingRuleMapper.toRatingRulesResponse(updatedRatingRules);
    }

    public List<RatingRuleResponse> getRatingRules() {
        log.info("Start getting rating rules");
        List<RatingRules> loadedRatingRules = ratingRuleRepository.findAll();

        return loadedRatingRules.stream()
                .map(ratingRuleMapper::toRatingRulesResponse)
                .toList();
    }

    public void deleteRatingRule(UUID id) {
        log.info("Start deleting rating rule with id = {}", id);

        validateRatingRuleById(id);
        ratingRuleRepository.deleteById(id);
    }

    public RatingRules loadRatingRules(UUID id) {
        return ratingRuleRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Rating rule with id = " + id + " not found")
                );
    }

    private void validateRatingRuleByName(String name) {
        if (ratingRuleRepository.existsByName(name)) {
            throw new NotUniqueException("Rating rule with name " + name + " already exists");
        }
    }

    private void validateRatingRuleById(UUID id) {
        if (!ratingRuleRepository.existsById(id)) {
            throw new NotFoundException("Rating rule with id " + id + " not found");
        }
    }
}
