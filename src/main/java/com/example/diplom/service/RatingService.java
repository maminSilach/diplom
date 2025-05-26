package com.example.diplom.service;


import com.example.diplom.dto.request.RatingRequest;
import com.example.diplom.dto.response.RatingResponse;
import com.example.diplom.entity.Rating;
import com.example.diplom.entity.rules.RatingRules;
import com.example.diplom.exception.NotFoundException;
import com.example.diplom.exception.NotUniqueException;
import com.example.diplom.mapper.rating.RatingMapper;
import com.example.diplom.repository.rating.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingRuleService ratingRuleService;
    private final RatingMapper ratingMapper;

    @Transactional
    public RatingResponse createRating(RatingRequest ratingRequest) {
        validateRatingByName(ratingRequest.getName());
        log.info("Start creating rating with name = {}", ratingRequest.getName());

        List<RatingRules> ratingRules = ratingRequest.getRatingRuleIds().stream().map(ratingRuleService::loadRatingRules).toList();
        Rating ratingToSave = ratingMapper.toRating(ratingRequest, ratingRules);
        Rating savedRating = ratingRepository.save(ratingToSave);
        ratingRules.forEach(ratingRule -> ratingRule.setRating(savedRating));

        log.info("End creating rating with name = {} and id = {}", savedRating.getName(), savedRating.getId());

        return ratingMapper.toRatingResponse(savedRating);
    }

    public RatingResponse getRating(UUID id) {
        log.info("Start getting rating with id = {}", id);
        Rating loadedRating = loadRating(id);
        return ratingMapper.toRatingResponse(loadedRating);
    }

    public Rating loadRating(UUID id) {
        return ratingRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Rating with id = " + id + " not found")
                );
    }

    public List<RatingResponse> getRatings() {
        log.info("Start getting rating");
        List<Rating> loadedRatingRules = ratingRepository.findAll();

        return loadedRatingRules.stream()
                .map(ratingMapper::toRatingResponse)
                .toList();
    }

    public RatingResponse updateRating(UUID id, RatingRequest ratingRequest) {
        log.info("Start updating rating with id = {} and body = {}", id, ratingRequest);

        List<RatingRules> ratingRules = ratingRequest.getRatingRuleIds().stream().map(ratingRuleService::loadRatingRules).toList();
        Rating loadedRating = loadRating(id);
        Rating ratingToUpdate = ratingMapper.updateRating(loadedRating,ratingRequest, ratingRules);
        Rating updatedRating = ratingRepository.save(ratingToUpdate);
        ratingRules.forEach(ratingRule -> ratingRule.setRating(updatedRating));

        log.info("End updating rating with name = {} and id = {}", updatedRating.getName(), updatedRating.getId());

        return ratingMapper.toRatingResponse(updatedRating);
    }

    public void deleteRating(UUID id) {
        log.info("Start deleting rating with id = {}", id);

        validateRatingById(id);
        ratingRepository.deleteById(id);
    }

    private void validateRatingById(UUID id) {
        if (!ratingRepository.existsById(id)) {
            throw new NotFoundException("Rating with id " + id + " not found");
        }
    }

    private void validateRatingByName(String name) {
        if (ratingRepository.existsByName(name)) {
            throw new NotUniqueException("Rating with name " + name + " already exists");
        }
    }
}
