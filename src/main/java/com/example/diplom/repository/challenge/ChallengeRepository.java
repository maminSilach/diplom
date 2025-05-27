package com.example.diplom.repository.challenge;

import com.example.diplom.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {

    boolean existsByName(String name);
}
