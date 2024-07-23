package com.sergeineretin.tennisScoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatchDto {
    private int id;
    private int player1;
    private int player2;
    private int score1;
    private int score2;
}
