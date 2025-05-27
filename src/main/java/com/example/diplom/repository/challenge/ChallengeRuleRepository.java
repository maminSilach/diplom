package com.example.diplom.repository.challenge;

import com.example.diplom.entity.rules.ChallengeRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChallengeRuleRepository extends JpaRepository<ChallengeRules, UUID> {

    boolean existsByName(String name);
}
