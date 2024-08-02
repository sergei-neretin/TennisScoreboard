package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.model.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.TieBreakUpdater;

import java.util.Objects;

public class TieBreakUpdaterImpl implements TieBreakUpdater {
    @Override
    public void updateTieBreak(Match match, String playerWhoWinsPoint) {
        updateScore(match, playerWhoWinsPoint);
        if (match.getPoints1() >= 7 && match.getPoints1() - match.getPoints2() >= 2 ) {
            match.setSet1(match.getSet1() + 1);
            match.resetGames();
            match.resetPoints();
        } else if (match.getPoints2() >= 7 && match.getPoints2() - match.getPoints1() >= 2) {
            match.setSet2(match.getSet2() + 1);
            match.resetGames();
            match.resetPoints();
        }
    }
    private void updateScore(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(playerWhoWinsPoint, match.getName1())) {
            match.setPoints1(match.getPoints1() + 1);
        } else {
            match.setPoints2(match.getPoints2() + 1);
        }
    }

    @Override
    public boolean tieBreakCondition(Match match) {
        return match.getGame1() == 6 && match.getGame2() == 6;
    }
}
