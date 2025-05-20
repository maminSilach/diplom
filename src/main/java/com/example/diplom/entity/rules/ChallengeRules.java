package com.example.diplom.entity.rules;


import com.example.diplom.entity.Challenge;
import com.example.diplom.entity.Event;
import com.example.diplom.entity.Rating;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "challenge_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeRules {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "percent")
    private BigDecimal percent = BigDecimal.ZERO;

    @OneToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private Challenge challenge;
}
