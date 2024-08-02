package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.model.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.GameUpdater;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.SetsUpdater;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.TieBreakUpdater;

public class SetsUpdaterImpl implements SetsUpdater {
    private final GameUpdater gameUpdater;
    private final TieBreakUpdater tieBreakUpdater;

    public SetsUpdaterImpl(GameUpdater gameUpdater, TieBreakUpdater tieBreakUpdater) {
        this.gameUpdater = gameUpdater;
        this.tieBreakUpdater = tieBreakUpdater;
    }
    @Override
    public void updateSet(Match match, String playerWhoWinsPoint) {
        if (tieBreakUpdater.tieBreakCondition(match)) {
            tieBreakUpdater.updateTieBreak(match, playerWhoWinsPoint);
        } else {
            gameUpdater.updateGame(match, playerWhoWinsPoint);
            if (match.getGame1() == 6 && match.getGame2() <= 4) {
                match.setSet1(match.getSet1() + 1);
                match.resetGames();
            } else if (match.getGame2() == 6 && match.getGame1() <= 4) {
                match.setSet2(match.getSet2() + 1);
                match.resetGames();
            } else if (match.getGame1() == 7 && match.getGame2() <= 5) {
                match.setSet1(match.getSet1() + 1);
                match.resetGames();
            } else if (match.getGame2() == 7 && match.getGame1() <= 5) {
                match.setSet2(match.getSet1() + 1);
                match.resetGames();
            }
        }
    }

}
