package com.sergeineretin.tennisScoreboard.dto;

import com.sergeineretin.tennisScoreboard.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatchScoreDto {
    private long id;
    private PlayerDto player1;
    private PlayerDto player2;
    private PlayerDto winner;
    private int score1;
    private int score2;
}
