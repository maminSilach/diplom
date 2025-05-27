package com.example.diplom.repository.badge;

import com.example.diplom.entity.rules.BadgeRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BadgeRuleRepository extends JpaRepository<BadgeRules, UUID> {

    boolean existsByName(String name);
}
