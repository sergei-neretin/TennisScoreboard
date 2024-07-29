package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;

public class MatchTestUtils {
    private MatchTestUtils() {}

    public static Match getMatch() {
        return Match.builder()
                .id1(1)
                .id2(2)
                .build();
    }
}
