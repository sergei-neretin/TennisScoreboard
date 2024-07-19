package com.sergeineretin.tennisScoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Matches")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "Player1", foreignKey = @ForeignKey(name = "FK"))
    private Player player1;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "Player2", foreignKey = @ForeignKey(name = "FK"))
    private Player player2;
    @JoinColumn(name = "Winner", foreignKey = @ForeignKey(name = "FK"))
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Player winner;
}
