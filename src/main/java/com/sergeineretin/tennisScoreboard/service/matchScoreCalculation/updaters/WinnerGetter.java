package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters;

import com.sergeineretin.tennisScoreboard.dto.Match;

import java.util.Optional;

public interface WinnerGetter {
    Optional<String> getWinner(Match match);
}
