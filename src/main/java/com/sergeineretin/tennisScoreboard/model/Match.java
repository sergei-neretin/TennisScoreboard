package com.sergeineretin.tennisScoreboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Match {
    private String name1;
    private String name2;
    private int points1;
    private int points2;
    private int game1;
    private int game2;
    private int set1;
    private int set2;

    public void resetSets() {
        set1 = 0;
        set2 = 0;
    }
    public void resetGames() {
        game1 = 0;
        game2 = 0;
    }
    public void resetPoints() {
        points1 = 0;
        points2 = 0;
    }
}
