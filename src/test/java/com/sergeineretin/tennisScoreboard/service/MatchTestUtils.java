package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;

public class MatchTestUtils {
    private MatchTestUtils() {}

    public static Match getMatch() {
        return Match.builder()
                .name1("John Doe")
                .name2("Jane Doe")
                .build();
    }
}
