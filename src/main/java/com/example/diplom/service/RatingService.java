package com.example.diplom.service;


import com.example.diplom.dto.request.RatingRequest;
import com.example.diplom.dto.response.RatingResponse;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.rules.RatingRules;
import com.example.diplom.mapper.rating.RatingMapper;
import com.example.diplom.repository.rating.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;



    public RatingResponse createRating(RatingRequest ratingRequest) {
        log.info("Start creating rating with name = {}", ratingRequest.getName());

//        Event event = eventService.loadEvent(ratingRuleRequest.getEventId());
//
//        RatingRules ratingRulesToSave = ratingRuleMapper.toRatingRules(ratingRuleRequest, event);
//        RatingRules savedRatingRules = ratingRuleRepository.save(ratingRulesToSave);
//
//        log.info("End creating rating rule with name = {} and id = {}", savedRatingRules.getName(), savedRatingRules.getId());
//
//        return ratingRuleMapper.toRatingRulesResponse(savedRatingRules);
        return null;
    }


}
