package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.model.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.WinnerGetter;

import java.util.Optional;

public class WinnerGetterImpl implements WinnerGetter {

    public Optional<String> getWinner(Match match) {
        if (match.getSet1() == 2 && match.getSet2() < 2) {
            return Optional.of(match.getName1());
        } else if (match.getSet2() == 2 && match.getSet1() < 2) {
            return Optional.of(match.getName2());
        } else {
            return Optional.empty();
        }
    }
}
