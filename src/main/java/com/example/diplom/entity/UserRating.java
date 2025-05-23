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

import java.util.UUID;


@Entity
@Table(name = "user_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "score")
    private Integer score;

    @Column(name = "received")
    private boolean received = false;

    @OneToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "id")
    private Rating rating;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
