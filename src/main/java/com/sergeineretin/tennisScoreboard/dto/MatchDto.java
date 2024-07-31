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
    private long id;
    private PlayerDto player1;
    private PlayerDto player2;
    private PlayerDto winner;
}
