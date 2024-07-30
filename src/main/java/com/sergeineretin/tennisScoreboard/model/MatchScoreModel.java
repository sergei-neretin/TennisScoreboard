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
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Player1", nullable = false)
    private Player player1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Player2", nullable = false)
    private Player player2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Winner", nullable = false)
    private Player winner;
}
