package com.example.diplom.repository.rating;

import com.example.diplom.entity.rules.RatingRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRuleRepository extends JpaRepository<RatingRules, UUID> {

    boolean existsByName(String name);
}
