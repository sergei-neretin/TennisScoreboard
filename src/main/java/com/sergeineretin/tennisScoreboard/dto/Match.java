package com.sergeineretin.tennisScoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Match {
    private long id1;
    private long id2;
    private int points1;
    private int points2;
    private int game1;
    private int game2;
    private int set1;
    private int set2;
}
