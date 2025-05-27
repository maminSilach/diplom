package com.example.diplom.entity;

import com.example.diplom.entity.enums.EntityStatus;
import com.example.diplom.entity.rules.ChallengeRules;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "challenge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "imageUri")
    private String imageUri;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ARCHIVE;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeRules> challengeRules = new ArrayList<>();
}
