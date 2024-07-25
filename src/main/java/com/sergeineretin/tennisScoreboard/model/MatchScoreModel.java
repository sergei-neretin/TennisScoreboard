package com.sergeineretin.tennisScoreboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Matches")
public class MatchScoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Player1", nullable = false)
    private long player1Id;

    @Column(name = "Player2", nullable = false)
    private long player2Id;

    @Column(name = "Winner")
    private long winnerId;
}
