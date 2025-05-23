package com.example.diplom.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "user_challenge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserChallenge {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "progress")
    private BigDecimal progress;

    @Column(name = "received")
    private boolean received = false;

    @OneToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    private Challenge challenge;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
