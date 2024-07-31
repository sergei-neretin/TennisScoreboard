package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.TieBreakUpdater;

import java.util.Objects;

public class TieBreakUpdaterImpl implements TieBreakUpdater {
    @Override
    public void updateTieBreak(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(playerWhoWinsPoint, match.getName1())) {
            match.setPoints1(match.getPoints1() + 1);
        } else {
            match.setPoints2(match.getPoints2() + 1);
        }

        if (match.getPoints1() == 7 && match.getPoints2() < 6) {
            match.setSet1(match.getSet1() + 1);
            match.resetGames();
        } else if (match.getPoints2() == 7 && match.getPoints1() < 6) {
            match.setSet2(match.getSet2() + 1);
            match.resetGames();
        } else if((match.getPoints1() == 6 && match.getPoints2() == 6)) {
            match.resetPoints();
        }
    }

    @Override
    public boolean tieBreakCondition(Match match) {
        return match.getGame1() == 6 && match.getGame2() == 6;
    }
}
